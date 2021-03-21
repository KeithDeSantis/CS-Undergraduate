Author: Kush Shah
3516 Project 1
Date: 09/18/20


Server:
	From inside the /Server directory run 'make' to compile the program.
	This will create the './server' executable.
	To start the server run './server <port>'

    Example:
        ./server 15686


Client:
	From inside the /Client directory run 'make' to compile the program.
	This will create the './client' executable.
	To start he server run './client [<option>] <URL> <port>

	Options:
		-p	Print RTT time in milliseconds

    Example:
        ./client www.google.com 80   

        ./client www.mit.edu 80 

        ./client linux.wpi.edu 15686  (TMDG.html from wpi linux server)


	When running ./client the whole response is recorded in 'index.html' inside the Client directory. 
