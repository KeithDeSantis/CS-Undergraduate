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
import json

ourVMs = ["52:54:00:00:05:26", "52:54:00:00:05:27", "52:54:00:00:05:28", "52:54:00:00:05:29", "ff:ff:ff:ff:ff:ff"]

class SimpleSwitch12(app_manager.RyuApp):
    OFP_VERSIONS = [ofproto_v1_2.OFP_VERSION]

    """ Thread that writes data structures to file for persistence """
    def write_history_to_file(self):
        while True:
            sleep(5)
            if(len(self.history_dict) == 0):
                continue
            print("Thread writing...")
            file = open('history_dict.json', 'w')
            json_object = json.dumps(self.history_dict, indent=4)
            file.write(json_object)
            file.close()

    def __init__(self, *args, **kwargs):
        super(SimpleSwitch12, self).__init__(*args, **kwargs)
        self.mac_to_port = {}
        self.history_dict = {'address_comms' : {'ip': {}, 'eth' : {}}, 'eth_to_ip' : {}, 'port_comms' : {}} # srcMAC : { srcPort : { dstMAC: dstPort } }
        self.file_write_thread = threading.Thread(target=self.write_history_to_file)
        self.file_write_thread.start()

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

        all_protocols = self.get_protocols_from_packet(pkt)
        self.record_from_protocols(all_protocols, ethsrc, ethdst)

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

    """ Helper to get all protocols a packet has and returns a list of those protocols """
    def get_protocols_from_packet(self, pkt):
        all_protocols = {}
        all_protocols['ip'] = pkt.get_protocols(ipv4.ipv4)
        all_protocols['eth'] = pkt.get_protocols(ethernet.ethernet)
        all_protocols['tcp'] = pkt.get_protocols(tcp.tcp)
        return all_protocols

    """ Helper to record data from protocols. Takes ethsrc and ethdst since they are always there """
    def record_from_protocols(self, all_protocols, ethsrc, ethdst):
        self.recordEthData(ethsrc, ethdst)
        if all_protocols['ip']:
            self.recordIPData(all_protocols['ip'], ethsrc, ethdst)
        if all_protocols['tcp']:
            self.recordPortData(all_protocols['tcp'], ethsrc, ethdst)
        
    """ Record ports related to MAC Addresses """
    def recordPortData(self, tcp_protocol, ethsrc, ethdst):
        t_cp = tcp_protocol[0]
        src_port = t_cp.src_port
        dst_port = t_cp.dst_port
        self.history_dict['port_comms'].setdefault(ethsrc, {})
        self.history_dict['port_comms'][ethsrc].setdefault(src_port, {})
        self.history_dict['port_comms'][ethsrc][src_port].setdefault(ethdst, -1)
        self.history_dict['port_comms'][ethsrc][src_port][ethdst] = dst_port
        self.history_dict['port_comms'].setdefault(ethdst, {})
        self.history_dict['port_comms'][ethdst].setdefault(dst_port, {})
        self.history_dict['port_comms'][ethdst][dst_port].setdefault(ethsrc, -1)
        self.history_dict['port_comms'][ethdst][dst_port][ethsrc] = src_port
        return

    """ Helper to record info from an IP packet """
    def recordIPData(self, ip_protocol, ethsrc, ethdst):
        ip = ip_protocol[0]
        ipdst = ip.dst
        ipsrc = ip.src
        # Record communications between IPs
        self.history_dict['address_comms']['ip'].setdefault(ipsrc, {})
        self.history_dict['address_comms']['ip'][ipsrc].setdefault(ipdst, 0)
        self.history_dict['address_comms']['ip'][ipsrc][ipdst] += 1
        # Correlate MAC address to IP
        self.history_dict['eth_to_ip'].setdefault(ethsrc, [])
        if(ipsrc not in self.history_dict['eth_to_ip'][ethsrc]):
            self.history_dict['eth_to_ip'][ethsrc].append(ipsrc)
        self.history_dict['eth_to_ip'].setdefault(ethdst, [])
        if(ipdst not in self.history_dict['eth_to_ip'][ethdst]):
            self.history_dict['eth_to_ip'][ethdst].append(ipdst)
    
    """ Helper to record info from an Ethernet packet """
    def recordEthData(self, ethsrc, ethdst):
        self.history_dict['address_comms']['eth'].setdefault(ethsrc, {})
        self.history_dict['address_comms']['eth'][ethsrc].setdefault(ethdst, 0)
        self.history_dict['address_comms']['eth'][ethsrc][ethdst] += 1