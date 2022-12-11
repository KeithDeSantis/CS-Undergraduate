# **<u>`Mission 3 README`</u>**

Kush Shah, Jakob Misbach, Keith DeSantis

___

This is the submission for Mission 3 for CS4404 Network Security, B Term 2022.

The directory `VMFiles` contains all relevant files from our 4 VMs, separated into 4 directories: `VM1Home`, `VM2Home`, `VMHome3`, `VMHome4`.

Included files are:

* This `README.md`
* `Mission 3 Writeup.pdf` Our Mission writeup

**<u> In `VM1Home`: </u>**
* `victimOne.txt` A file describing this VM's role and IP 
* `coverTrafficSim.py` The Python script used to generate HTTP cover traffic from Host 1.
* `OVSVM1.sh` Script to install and setup OpenvSwitch on VM1 from scratch
* `trafficOutput.txt` A file which that all output from cover traffic was piped to

**<u> In `VM2Home`: </u>**

* `victimTwo.txt` A file describing this VM's role and IP 
* `coverTrafficSim.py` The Python script used to generate HTTP cover traffic from Host 2.
* `OVSVM2.sh` Script to install and setup OpenvSwitch on VM2 from scratch
* `trafficOutput.txt` A file which that all output from cover traffic was piped to
* `sniffer` A directory containing `sniffer.py`, the Python script that the bot will run to communicate with it's command and control server.

**<u> In `VM3Home`: </u>**

* `victimThree.txt` A file describing this VM's role and IP 
* `coverTrafficSim.py` The Python script used to generate HTTP cover traffic from Host 3.
* `OVSVM3.sh` Script to install and setup OpenvSwitch on VM3 from scratch
* `trafficOutput.txt` A file which that all output from cover traffic was piped to

**<u> In `VM4Home`: </u>**

* `attacker.txt` A file describing this VM's role and IP 
* `coverTrafficSim.py` The Python script used to generate HTTP cover traffic from Host 4.
* `trafficOutput.txt` A file which that all output from cover traffic was piped to
* `controller_stuff` A directory container files related to the SDN controller IDS
    * `history_dict.json` The JSON file populated by `learningSwitch.py` and used by the controllers to store historical connection information about the network
    * `learningSwitch.py` The controller used to build a set of historical data from network communications
    * `simpleIDSController.py` The IDS controller that parses and validates HTTP traffic on a packet to packet basis, vulnerable to our attack
    * `pairingController.py` The more sophisticated IDS controller that performs all validation of HTTP traffic on a packet basis, but also maintains a conversation level context on communications, matching responses to their respective requests
    * `malicious_traffic_log.log` The log file which the controller writes to when it detects suspicious HTTP traffic and blocks it
* `commander.py` The command server for the botnet