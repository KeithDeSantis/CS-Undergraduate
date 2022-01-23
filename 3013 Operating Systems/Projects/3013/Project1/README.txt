Kush Shah, kshah2@wpi.edu
Keith DeSantis, kwdesantis@wpi.edu
CS3013 Project1 02/06/2021

Scenario 1 - Prolific
For the prolific.c scenario the goal was to fork() (f) a random number (num) of children [10,15]. Each location in in children[num] contains another random number, 
which is then used in two different calculations done by the child process.
		1) exitCode = (children[i] % 50) +1; The exit code to be used by child process i.
		2) exitTime = (children[i] % 3) + 1; The time to wait before calling exit() for child precess i.
Once the child has exited the parent prints out relevent information such as:
		1) Child PID; from f 
		2) Child exitCode; retrived using waitpid() and WEXITSTATUS();
Once this process has repeated num times the parent exits normally with code 0 if no errors occured.  

Scenario 2 - Lifespan Generation

For the generation.c scenario the goal was to create a line of decendants (rand() % ((11-1)-7)+7) [7,11] long (lifeCount).
lifeCount is saved as a global variable (created before fork()ing) so that each child could modify it during their life. 
While(lifeCount > 0){
Each child will simply:
	1) Print out their own PID
	2) Print out their own value of lifeCount
	3) Print out the number of decendants following them (their childs future value of lifeCount)
	4) exit() with code lifeCount if they are to have no decendants (lifeCount == 0)
					OR (lifeCount != 0)
	4) decrement lifeCount if they are to have decendants
The Parent will:
	1) Print out which child they are currently waiting on
	2) Print out which child has exit()ed and with what exitCode (WEXITSTATUS)
	3) exit() with 1 + childExitStatus 
} Once lifeCount == 0 then the "family" has ended as their are no more decendants.

Scenario 3 - Explorer

For the explorer.c scenario, the goal was to change the working directory to "/home", "/proc", "/proc/sys", "/usr", "/usr/bin", "/bin" in a random order, and at each fork off a child who would run "ls -alh."
The directories that have already been visited were tracked using two one to one arrays called  sites[] and visited[]
		1) char* sites[6] = { "/home", "/proc", "/proc/sys", "/usr", "/usr/bin", "/bin" };
    		2) int visited[6] = { -1,-1,-1,-1,-1,-1 };
Once the process determined a valid target that hadn't been visited the -1 in the associated index of the target in the visited[] would be replaced with said index, indicated the site had been visited.
Once in a new working directory the parent will:
	1) Fork off a new child	
	2) Print that it is waiting for the given child
	3) Wait for the given child
	4) Print that the child is done and repeat the searching process
Once created a child will:
	1) Print that it is executing ls -alh
	2) Execute ls -alh
	3) Exit

Once the visits variable is equal to six the process had visited every site and exits.

Scenario 4 - Slug

For the slug.c scenario the start of the program is very similar to Scenario 1 - Prolific. For the first time the scenario requires an argument to run[1 2 3 4]. 
This forced the implementation of error checking with the arguments. The argument would indicate which of the four pre-made seed files would be used as the random seed in the srand() function. Then the program uses [rand() % ((5-1) -1) + 1]
to generate a random number of seconds, [1,5] to wait before continuing. Then based of the results of a simulated coin flip the program would execute one of two commands.
		If the flip resulted in a value of 0:
			- execvp() would execute the command 'last -d --fulltimes'
		Else if the flip resulted in a value of 1:
			- execvp() would execute the command 'id -u'

Scenario 5 - Slug Race

For the slugrace.c scenario the code is very similar to Scenario 1 - Prolific. First, 4 children are fork()ed with their PIDs stored in an array (int ID[4]).
The parent process increments pCount just before the call to fork() so that each child process can know which argument to run './slug' with, when calling execvp().
Once the children have been forked off, the parent enters a loop with the terminating condition !allDone (bool) which will be set to true once all 4 children have completed './slug [1234]'.
	How we determine if a child has completed:
		We hold an integer value called status, which will be set by waitpid(WAITANY, &status, WNOHANG) (should always be positive integer if nothing goes wrong with './slug [1234]'.
		if(status > 0){//child has completed} 
	How we determine the race has concluded:
		Once a child has completed their PID is replaced with -1 in the ID[4] array. if(sum of ID[4] == -4){allDone = true;}
  


How to use:

Scenario 1 -Prolific
	
	Once in the directory containing the Makefile, run either 'make all' OR 'make prolific'. This will create an executable './prolific'
	'./prolific' requires no arguments, simply run './prolific'. 

Scenario 2 - Lifespan Generation
	Once in the directory containing the Makefile, run either 'make all' OR 'make generation'. This will create an executable './generation'
	'./generation' requires no arguments, simply run './generation'.

Scenario 3 - Explorer
	Once in the directory containing the Makefile, run either 'make all' OR 'make explorer'. This will create an executable './explorer'
	'./explorer' requires no arguments, simply run './explorer'

Scenario 4 - Slug
	Once in the directory containing the Makefile, run either 'make all' OR 'make slug'. This will create an executable './slug'
	Unlike the other scenarios './slug' requires an argument to run.
		Legal arguments are [1,2,3,4]. The argument is used to pick a seed file. The different seeds are:
			{ 1 : 69420,
			  2 : 42069,
	  		  3 : 12345,
			  4 : 5318008}
		To run simply use './slug {1,2,3,4}'
Scenario 5 - Slug race
	Once in the directory containing the Makefile, run either 'make all' OR 'make slugrace'. This will create an executable './slugrace'.
	'./slugrace' requires no argument, simply run './slugrace'

***If a scenario does NOT require arguments then anything supplied after the executable call will be ignored and thrown out***
***If a scenario DOES require arguments then only the specified arguments are legal and any illegal arguments may cause the scenario to break or run with abnormal behavior***


MAKEFILE:

	Targets:
		prolific   - compiles prolific.c and returns './prolific'
		generation - compiles generation.c and returns './generation'
		explorer   - compiles explorer.c and returns './explorer'
		slug       - compiles slug.c and returns './slug'
		slugrace   - compiles slugrace.c and returns './slugrace'
		all        - compiles all targets listed above and returns all executables
		clean      - removes all executables generated by the targets above
	Use:	
		In the project directory run 'make {target}'	



