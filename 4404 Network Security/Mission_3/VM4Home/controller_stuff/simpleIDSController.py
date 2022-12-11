from ryu.base import app_manager
from ryu.controller import ofp_event
from ryu.controller.handler import MAIN_DISPATCHER
from ryu.controller.handler import set_ev_cls
from ryu.ofproto import ofproto_v1_2
from ryu.lib.packet import packet
from ryu.lib.packet import * #ethernet
from ryu.lib.packet import ether_types
import threading
from time import sleep
from bs4 import BeautifulSoup
import json
import re

# In order to fit into the project, we are assuming we will only be communicating with HTTP
ourVMs = ["52:54:00:00:05:26", "52:54:00:00:05:27", "52:54:00:00:05:28", "52:54:00:00:05:29", "ff:ff:ff:ff:ff:ff"]
httpPorts = [80, 8888, 8080, 8000]

class SimpleSwitch12(app_manager.RyuApp):
    OFP_VERSIONS = [ofproto_v1_2.OFP_VERSION]

    def __init__(self, *args, **kwargs):
        super(SimpleSwitch12, self).__init__(*args, **kwargs)
        self.mac_to_port = {}
        with open("history_dict.json", "r") as f:
            self.history_dict = json.load(f)
        # { C (Communication Channel) : V (Number of Waiting Requests) }
        # Key: Request Channel of Communication (dst_eth, dst_port, src_eth, src_port)
        # Value: Number of "waiting" requests over this channel
        # Everytime we get a valid request on channel C, we increment C's value V by 1
        # Everytime we get a seemingly valid Response Code HTTP response on C, we decrement V by 1
        # This way, we can ensure each response has had a request, and that the number of requests and responses match

    def add_flow(self, datapath, port, dst, src, actions):
        ofproto = datapath.ofproto

        match = datapath.ofproto_parser.OFPMatch(in_port=port,
                                                 eth_dst=dst,
                                                 eth_src=src)
        inst = [datapath.ofproto_parser.OFPInstructionActions(
                ofproto.OFPIT_APPLY_ACTIONS, actions)]

        mod = datapath.ofproto_parser.OFPFlowMod(
            datapath=datapath, cookie=0, cookie_mask=0, table_id=0,
            command=ofproto.OFPFC_ADD, idle_timeout=0, hard_timeout=0,
            priority=0, buffer_id=ofproto.OFP_NO_BUFFER,
            out_port=ofproto.OFPP_ANY,
            out_group=ofproto.OFPG_ANY,
            flags=0, match=match, instructions=inst)
        datapath.send_msg(mod)

    """ Main packet in handler """
    @set_ev_cls(ofp_event.EventOFPPacketIn, MAIN_DISPATCHER)
    def _packet_in_handler(self, ev):
        msg = ev.msg
        datapath = msg.datapath
        ofproto = datapath.ofproto
        in_port = msg.match['in_port']

        pkt = packet.Packet(msg.data)
        eth = pkt.get_protocols(ethernet.ethernet)[0]

        if eth.ethertype == ether_types.ETH_TYPE_LLDP:
            # ignore lldp packet
            return
        ethdst = eth.dst
        ethsrc = eth.src

        # Get protocols
        all_protocols = self.get_protocols_from_packet(pkt)

        # Inspect HTTP traffic
        if all_protocols['tcp']:
            # If it is on the expected ports, we think it is HTTP
            if self.is_http_packet(pkt):
                if not self.is_valid_http(pkt, ethsrc, ethdst):
                    # Log the alert
                    f = open("malicious_traffic_log.log", "a")
                    f.write("Malformed HTTP traffic detected\n")
                    # Drop the packet
                    data = None
                    if msg.buffer_id == ofproto.OFP_NO_BUFFER:
                        data = msg.data
                    out = datapath.ofproto_parser.OFPPacketOut(
                        datapath=datapath, buffer_id=msg.buffer_id, in_port=in_port,
                        actions=[], data=data)
                    datapath.send_msg(out)
                    return

        dpid = datapath.id
        self.mac_to_port.setdefault(dpid, {})

        #self.logger.info("packet in dpid: %s src: %s dst: %s in_port: %s", dpid, src, dst, in_port)

        # learn a mac address to avoid FLOOD next time.
        self.mac_to_port[dpid][ethsrc] = in_port

        if ethdst in self.mac_to_port[dpid]:
            out_port = self.mac_to_port[dpid][ethdst]
        else:
            out_port = ofproto.OFPP_FLOOD

        actions = [datapath.ofproto_parser.OFPActionOutput(out_port)]

        # install a flow to avoid packet_in next time
        if out_port != ofproto.OFPP_FLOOD and (ethsrc not in ourVMs or ethdst not in ourVMs):
            #self.logger.info("Adding flow on DP %s through %s from source: %s to dest: %s", dpid, in_port, dst, src)
            self.add_flow(datapath, in_port, ethdst, ethsrc, actions)

        data = None
        if msg.buffer_id == ofproto.OFP_NO_BUFFER:
            data = msg.data

        out = datapath.ofproto_parser.OFPPacketOut(
            datapath=datapath, buffer_id=msg.buffer_id, in_port=in_port,
            actions=actions, data=data)
        datapath.send_msg(out)


    """ Filter packets to HTTP requests and replies on HTTP ports """
    def is_http_packet(self, pkt):
        payload = str(pkt.data)
        t_cp = pkt.get_protocols(tcp.tcp)[0]
        src_port = t_cp.src_port
        dst_port = t_cp.dst_port
        correct_port = (src_port in httpPorts) or (dst_port in httpPorts)
        request = (("GET" in payload) or ("POST" in payload) or ("PUT" in payload)) or b"\x0D\x0A\x0D\x0A" in pkt.data
        reply = "content-type" in payload.lower()
        return correct_port and (request or reply)

    """ Run on TCP packet to determine if it is a valid HTTP request or reply  """
    #* Checks:
        #* If it is a request or reply (or just malformed)
        #* If a request,
            #* Ensures the Hostname and port are valid and the request ends in a carriage return
        #* If a reply, check if it is a response code reply or served HTML
            #* If reponse code
                #* Check that the response code is formatted right and is a valid code
            #* If HTML
                #* Check that HTML is valid syntax
    def is_valid_http(self, pkt, ethsrc, ethdst):

        payload = str(pkt.data)
        dst_port = pkt.get_protocols(tcp.tcp)[0].dst_port #* Ensure the dst_port matches the port in the request (might not use, means bot MUST listen on HTTP port)

        # --------- HTTP Communication is a request --------- #
        if ("GET" in payload) or ("POST" in payload) or ("PUT" in payload):

            # Ensure the proper host is in the request, on a known IP with valid port
            expectedHostIPs = self.history_dict['eth_to_ip'][ethdst]
            validIPAndPort = False
            endsWithCarriageReturn = False
            # Check that the IP and Port match
            for ip in expectedHostIPs:
                for port in httpPorts:
                    if (f"Host: {ip}:{port}" in payload) or (f"Host: {ip}\x0D\x0A") in payload:
                        if dst_port == port: #* Ensure the dst_port matches the port in the request (might not use, means bot MUST listen on HTTP port)
                            validIPAndPort = True
            # Requests must end with '/r/n/r/n'
            endsWithCarriageReturn = pkt.data.endswith(b"\x0D\x0A\x0D\x0A")
            if not endsWithCarriageReturn:
                print("No carriage return")
            if not validIPAndPort:
                print("Not valid IP and port")
            return validIPAndPort and endsWithCarriageReturn
        
        # --------- HTTP Communication is a response --------- #
        elif "content-type" in payload.lower():

            # There are two types of HTTP response, the response code, and the served HTML

            # Response is the HTTP Response Code 100-599 and carriage return
            if pkt.data.endswith(b"\x0D\x0A\x0D\x0A"):
                # Find the response code, separated by spaces
                try:
                    ref_ind = payload.index('HTTP')
                    response_code = payload[ref_ind+9:ref_ind+12]
                    # Ensure it is a valid response code
                    isValid = int(response_code) in range(100,600)
                    if not isValid:
                        print("HTTP response code not valid")
                    return isValid

                # 'HTTP' substring not found in response code reply
                except:
                    print("HTTP substring not found in response")
                    return False

            # Response is the Served HTML
            else:
                # Ensure that the HTML is valid syntax
                isValid = bool(BeautifulSoup(pkt.data, "html.parser").find())
                if not isValid:
                    print("Invalid HTML")
                return isValid
        
        # The HTTP communication is very malformed
        else:
            print("HTTP communication not as expected")
            return False

    """ Helper to get all protocols a packet has and returns a list of those protocols """
    def get_protocols_from_packet(self, pkt):
        all_protocols = {}
        all_protocols['ip'] = pkt.get_protocols(ipv4.ipv4)
        all_protocols['eth'] = pkt.get_protocols(ethernet.ethernet)
        all_protocols['tcp'] = pkt.get_protocols(tcp.tcp)
        return all_protocols

    """ Check if src MAC:srcPort has communicated with dstMAC:dstPort before """
    def check_port_history(self, tcp_protocol, ethsrc, ethdst):
        t_cp = tcp_protocol[0]
        src_port = t_cp.src_port
        dst_port = t_cp.dst_port
        try:
            if dst_port in self.history_dict['port_comms'][ethsrc][src_port][ethdst]:
                return 1
            return 0
        except KeyError:
            return 0
    
    """ Requests a list of all flows from a datapath """
    def getFlows(self, datapath):
        parser = datapath.ofproto_parser
        req = parser.OFPFlowStatsRequest(datapath)
        datapath.send_msg(req)

    """ Whenever a switch replies with stats on its state this will run """
    @set_ev_cls(ofp_event.EventOFPStatsReply, MAIN_DISPATCHER)
    def stats_reply_handler(self, ev):
        msg = ev.msg
        ofp = msg.datapath.ofproto
        body = ev.msg.body
        # If the reply is a flow stat, inspect the flows on the switch
        if msg.type == ofp.OFPST_FLOW:
            self.flow_stats_reply_handler(body)

    """ Inspect switch's flows to ensure they have not been tampered with """
    def flow_stats_reply_handler(self, body):
        flows = []
        for stat in body:
            flows.append('table_id=%s '
                        'duration_sec=%d duration_nsec=%d '
                        'priority=%d '
                        'idle_timeout=%d hard_timeout=%d '
                        'cookie=%d packet_count=%d byte_count=%d '
                        'match=%s instructions=%s' %
                        (stat.table_id,
                        stat.duration_sec, stat.duration_nsec,
                        stat.priority,
                        stat.idle_timeout, stat.hard_timeout,
                        stat.cookie, stat.packet_count, stat.byte_count,
                        stat.match, stat.instructions))
        print(f"FlowStats: {flows}")