# **<u>`Mission 2 README`</u>**

Kush Shah, Jakob Misbach, Keith DeSantis

___

This is the submission for Mission 2 for CS4404 Network Security, B Term 2022.

The directory `VMFiles` contains all relevant files from our 4 VMs, separated into 4 directories: `VM1Home`, `VM2Home`, `VMHome3`, `VMHome4`.

Included files are:

* This `README.md`
* `Mission 2 Writeup.pdf` Our Mission writeup

**<u> In `VM1Home`: </u>**
* `client.txt` A file describing the VM role and IP
* `rootCA.crt` The certificate file for the certificate authority that authenticated the web server on VM 2, used on the client to import to Chrome, telling it to trust the CA
* `hosts` The modified `/etc/hosts` file to correlate `votes.gov` to `10.64.6.2`

**<u> In `VM2Home`: </u>**

* `realWebServer.txt` A file describing the VM role and IP
* `server.crt`, `cert.pem`, `key.pem`, `server.key` Files used for HTTPS on the web server for the POC that HTTPS doesn't defend against the timing attack.
* `simpleServer.py` A simple version of the server.
* `advancedServer.py` An advanced version of the server after our defense is implemented.
* `httpsAdvancedServer.py` An HTTPS version of the advanced server.
* `password.py` A python script responsible for account verification. This file is also responsible for sending the gerated authentication code to the authentication app.
* `passwordLocal.py` Has the same core functionality as `password.py` but with the exception of not sending the generated code.
* `key.py` A python file responsible for the cryptography portion. It can generate secrets and 6-digit authentication codes. 

**<u> In `VM3Home`: </u>**

* `authApp.txt` A file describing the VM role and IP
* `simpleAuthApp.txt` The simple authentication app used for the first scenario that receives a 2FA code from the server.
* `advancedAuthApp.py` The advanced authentication app used in both the first and second scenario that uses time and a secret key to generate a 2FA code
* `accounts_credentials_and_keys.txt` A list of the 3 pre-registered accounts' usernames, passwords, and secret keys 

**<u> In `VM4Home`: </u>**

* `attacker.txt` A file describing the VM role and IP
* `hosts` The modified `/etc/hosts` file to correlate `votes.gov` to `10.64.6.2`
* `rootCA.crt` The CA certificate that signed the web server's cert. Used to make the attacker trust the CA (for POC HTTPS not defending against timing attack)
* `stolenAccountInformation.txt` A file that the attacker writes the attack logs and account dashboard HTML to
* `timingAttack.py` A script used to perform the timing attack with `mitmproxy` over HTTP
* `httpsTimingAttack.py` A script used to perform the timing attack with `mitmproxy` over HTTPS as a POC that HTTPS does not stop the attack.