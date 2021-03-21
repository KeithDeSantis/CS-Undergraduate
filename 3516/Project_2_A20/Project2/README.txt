Author: Kush Shah
CS 3516 
October 4th 2020



Compilation:
	run 'make' in the project directory and then run './project2'



The program takes in 8 arguments
	1) Number of messages to simulate - the program will terminate when 
	   this number of messages sent has been achived. Should always be 
	   greater than one.
	
	2) Packet loss probability - Average number of packet expected to 
	   be lost. Between 0.0 and 1.0.

	3) Packet corruption proability - Average number of packets expected
	   to be corrupt. Between 0.0 and 1.0.

	4) Packet out-of-order probability - Average number of packets 
	   expected to be reordered. Between 0.0 and 1.0.

	5) Average time between messages from sender's layer5 - 
	   Time between sending each message must be greater than 0.
	   Reccomended value: 1000.

	6) Tracing level - level of verbosity when printing.
	   Can be set to 0, 1, 2. 

	7) Randomized - will randomize if the packet is lost corrupted 
           or out-of-order using random number generator. 
	   Can be set to 0 or 1.
	
	8) Bidirectional - Not implemented. 
	   Should always be run with biderictional set to 0.

