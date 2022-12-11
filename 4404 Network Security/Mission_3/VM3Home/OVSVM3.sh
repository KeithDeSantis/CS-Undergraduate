
#!/bin/bash

if [[ $EUID -ne 0 ]]; then
    echo "$0 is not running as root. Try using sudo."
    exit 2
fi

apt-get -y update
apt install -y openvswitch-switch
ovs-vsctl add-br mybridge
ovs-vsctl show
ifconfig mybridge up
ovs-vsctl add-port mybridge ens3
ovs-vsctl show
ifconfig ens3 0
ip addr flush dev ens3
ip addr add 10.64.6.3/8 dev ens3
ip link set mybridge up
ip addr flush dev ens3
ip addr add 10.64.6.3/8 dev mybridge
ovs-vsctl set bridge mybridge other-config:hwaddr=52:54:00:00:05:28
ovs-ofctl show mybridge
ovs-vsctl set-fail-mode mybridge standalone
ovs-vsctl show

apt install -y python3-ryu
