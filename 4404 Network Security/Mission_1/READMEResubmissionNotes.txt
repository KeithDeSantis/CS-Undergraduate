This is the resubmission for Mission 1 for Jakob Misbach, Kush Shah, and Keith DeSantis for CS4404 Network Security B Term 2022 WPI



Our resubmission focused on fleshing out our assumptions and learning outcomes in the Mission Debriefing Report.

Specifically, we explain our assumptions surrounding SSNs and their role as "trusted unique identifiers" and the process we are assuming is in place for registering and voting in order to address this feedback from our first submission:


    Infrastructure (3/5): From the vote website screenshot, I think the infrastructure setup has the flaw that malicious user could use other people’s SSN to vote.
    Debriefing (5/6): The report is generally understandable but misses details of the registration and vote process in the infrastructure section.


We discussed this with Shuwen in the forum and have added a section to the Writeup under the section III. Infrastructure Building Phase - 1. Web Server.


We also added explanation to the Defense section related to our TLS learning outcomes, to address this feedback:

    Defense (4/5): TLS installation for web server. Created CA, setup client to use CA. Tested with HTTPS and confirmed attack stopped. Reasonable implementation, learning outcomes around TLS not as obvious.


We expand on TLS and how it works more in the section V. Defense Phase - 1. SSL Certificates and HTTPS


