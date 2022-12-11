# **<u>`Mission 1 README`</u>**

Kush Shah, Jakob Misbach, Keith DeSantis

___

This is the submission for Mission 1 for CS4404 Network Security, B Term 2022.

The directory `VMFiles` contains all relevant files from our 4 VMs, separated into 4 directories: `VM1Home`, `VM2Home`, `VMHome3`, `VMHome4`.

Included files are:

* This `README.md`
* `Mission 1 Writeup.pdf` Our Mission writeup

**<u> In `VM1Home`: </u>**
* `client.txt` A file describing the VM role and IP
* `post.py` A script used for testing that sends a POST request to the web server, voting for KushShah with a valid SSN
* `rootCA.crt` The certificate file for the certificate authority on VM 3, used on the client to import to Chrome, telling it to trust the CA

**<u> In `VM2Home`: </u>**

* `realWebServer.txt` A file describing the VM role and IP
* `Flask` A directory containing our Flask web server, including the server's key and certificate (`server.key`, `server.crt`) HTTP and HTTPS versions of the server (`http-server.py`, `https-server.py`), a file containing database interacting functions (`db.py`), and miscellaneous files (HTML, CSS, etc)
* `netsec.sql` A file snapshot of our MySQL database at one point during testing

**<u> In `VM3Home`: </u>**

* `certAuth.txt` A file describing the VM role and IP
* The rootCA certificate and key for the CA (`rootCA.crt`, `rootCA.key`)
* A copy of the server's certificate and key (`server.crt`, `server.key`)
* Certificate authority configuration files (`cert.conf`, `csr.conf`, `rootCA.srl`, `server.csr`)

**<u> In `VM4Home`: </u>**

* `attacker.txt` A file describing the VM role and IP
* `poisonBallot.py` A script used to change all inctercepted votes to vote for KeithDeSantis
* `ransom.py` A script used to replace all votes with "RANSOM" and record the actual voter credentials and ballots to hold hostage. This was to simulate a situation where the attacker holds election results ransom.