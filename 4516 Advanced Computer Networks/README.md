# Advanced Computer Networks Project

This repository contains files related to Team 17's term project for CS4516: Advanced Computer Networks at\
WPI in D term of 2022

Our project involved creating and simulating an SDN network, using 4 VMs in an isolated network. OpenVSwitch was used to simulate an OpenFlow switch and the Ryu controller API was used to create an OpenFlow controller.

If you have any questions, feel free to contact us at kshah2@wpi.edu, sparks@wpi.edu, and kwdesantis@wpi.edu.

## Useful OVS Commands


* `ovs-vsctl show` – prints out the current state of the OVS, including bridges, controllers, connections, and ports

* `ovs-ofctl dump-flows br0` – prints out all the flows in br0’s flow table

* `ovs-ofctl del-flows br0` – deletes all flows from br0’s flow table (do this before testing an edited controller, cause the switch maintains flow rules even if the controller turns off)

* `ovs-vsctl list [thing to list, i.e. controller, bridge, port]` – lists all of the given kind of thing that are on the switch

* `ovs-vsctl add-br <Bridge Name>` - adds the named bridge to OVS

* `ovs-vsctl add-port <Bridge Name>` <Port Name> - adds the named port to the named bridge

* `ovs-vsctl del-br <Bridge Name>` - deletes the named bridge

* `ovs-vsctl del-port <Bridge Name> <Port Name>` - deletes the named port from the named bridge
---

### Commmands to run to set up ovs on Host 1:
```
ovs-vsctl add-br br0 -- set bridge br0 other_config:hwaddr=52:54:00:00:03:4a
ovs-vsctl add-port br0 ens3
ifconfig ens3 0
ifconfig br0 10.61.17.1
ovs-vsctl set-controller br0 tcp:10.61.17.4:6653
```
---
### Commmands to run to set up ovs on Host 2:
```
ovs-vsctl add-br br0 -- set bridge br0 other_config:hwaddr=52:54:00:00:03:4b
ovs-vsctl add-port br0 ens3
ifconfig ens3 0
ifconfig br0 10.61.17.2
ovs-vsctl set-controller br0 tcp:10.61.17.4:6653
```
---
### Commmands to run to set up ovs on Host 3:
```
ovs-vsctl add-br br0 -- set bridge br0 other_config:hwaddr=52:54:00:00:03:4c
ovs-vsctl add-port br0 ens3
ifconfig ens3 0
ifconfig br0 10.61.17.3
ovs-vsctl set-controller br0 tcp:10.61.17.4:6653
```
---
### Command to run controller on Host 4:
```
ryu-manager <controller-script> --ofp-listen-host 10.61.17.4 --ofp-tcp-listen-port 6653
```
