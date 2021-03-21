Kush Shah, kshah2@wpi.edu
Keith DeSantis, kwdesantis@wpi.edu
CS 3013, Project 4

Main:
	Before any scheduling algorithm is run we first create a job struct 
to represent each job. The struct has the following fields:
	int id = order of apperence in workload file
	bool finished = false
	bool started = false
	int length = read in from file
	double responseTime = 0
	double turnaroundTime = 0
	double waitTime = 0
	struct timespec start, completed, wait = 0
	struct job *next next job in list or (i+1) % numJobs

FIFO:
	read first job in list. Run till completed then move on to job.next
as the algorithm runs it saves responseTime, turnaroundTime and 
waitTime into the jobs fields for analysis later.

SJF:
	Finds the first job to run by 
looping through list of jobs and starting with the shortest one.
This job is set to strinct job * curr and run to completion.
Once done we search for the next job to run (the next shortest job).
curr.next is updated to this job and then curr is updated to curr.next.

RR:
	The first job run is recorded for analysis reasons.
We start from the first thing in the list run for timeSlice and continue. 
The critrion for running a job is if it is done or not.
There are two ways of running a job.
	1) if the job.length is > than timeSlice, run for timeSlice and move on
	2) job.length is < than timeSlice, run till completion set fields.

Workload 1:
	the workload created is [1,1,1,1,1]. Since all the jobs are shorter
than the timeSlice they run till completion and have the same wait and 
response time.

Workload 2:
	the workload created is [100000,1,1,1,1,1,1,1,1,1]. The workload 
takes advantage of the convoy effect. 

Workload 3:
	the workload created is [1,1,1,1,1]. This means that becuase
the lengths are less than the timeSlice and all the jobs are same 
all alrogithms run the same way

Workload 4:
	the workload created is [1,1,1,1,104]. becuase of the long final job
we get a large turnaround time. but becuase all precceding lengths
are small the response time is low. 

Workload 5:
	the workload created [3,9,12]. These values were calculated using
the equations for response time and turnaround time

