#include "scheduler.h"
char *policy;
char *file;
int buff[100] = {-1};
int numJobs = 0;
int timeSlice;

int main(int argc, char **argv){

    if(argc < 2 || argc > 4){
        printf("Wrong number of arguments.\n\tSupplied: %d\n\tNeeded: 3\n",argc-1);
        exit(1);
    }
    policy = argv[1];
    file = argv[2];
    FILE* testFile = fopen(file,"a+");
    for (int i = 0; i < 100; ++i) {
        int time = -1;
        fscanf(testFile, "%d", &time);
        if(time > 0){
            numJobs++;
        }
        buff[i] = time;
    }


    struct job jobList[numJobs];
    for (int i = 0; i < numJobs; ++i) {
        jobList[i].id=i;
        jobList[i].finished=false;
        jobList[i].started=false;
        jobList[i].length = buff[i];
        jobList[i].next = &jobList[(i+1) % numJobs];
        jobList[i].wait.tv_nsec = 0;
        jobList[i].wait.tv_sec = 0;
        jobList[i].waitTime = 0;
    }
    timeSlice = atoi(argv[3]);



    if(strcmp(policy,"FIFO") == 0){
        fifo(jobList);
    } else if(strcmp(policy,"SJF") == 0){
        sjf(jobList);
    } else if(strcmp(policy,"RR") == 0){
        rr(jobList);
    }else{
        printf("Policy must be {FIFO,SJF,RR}\n\tSupplied:%s\n",policy);
        exit(1);
    }
}

void fifo(struct job jobList[]){
    struct timespec origin,start,completed;
    clock_gettime(1,&origin);
    printf("Execution trace with FIFO:\n");
    int jobsCompleted = 0;
    struct job *first = &jobList[0];
    struct job *curr= &jobList[0];
    while(jobsCompleted < numJobs){
        if(curr->finished == false){
            clock_gettime(1,&start);
            sleep(curr->length);
            clock_gettime(1,&completed);
            curr->responseTime = (start.tv_sec + (start.tv_nsec)/pow(10,9)) - (origin.tv_sec + (origin.tv_nsec)/pow(10,9));
            curr->turnaroundTime = (completed.tv_sec + (completed.tv_nsec)/pow(10,9)) - (origin.tv_sec + (origin.tv_nsec)/pow(10,9));
            curr->waitTime = curr->responseTime;
            printf("Job %d ran for: %d\n", curr->id, curr->length);
            curr->finished = true;
            jobsCompleted++;
            curr = curr->next;
        }
    }
    printf("End of execution with FIFO.\n");
    analyze(first);
}

void sjf(struct job jobList[]){
    struct timespec origin,start,completed;
    clock_gettime(1,&origin);
    struct job *first = &jobList[0];
    for (int i = 0; i < numJobs; ++i) {
        if(jobList[i].length < first->length){
            first = &jobList[i];
        }
    }
    struct job *curr = first;
    int jobsCompleted = 0;

    printf("Execution trace with SJF:\n");
    while(jobsCompleted < numJobs){

        clock_gettime(1,&start);
        sleep(curr->length);
        clock_gettime(1,&completed);
        curr->responseTime = (start.tv_sec + (start.tv_nsec)/pow(10,9)) - (origin.tv_sec + (origin.tv_nsec)/pow(10,9));
        curr->turnaroundTime = (completed.tv_sec + (completed.tv_nsec)/pow(10,9)) - (origin.tv_sec + (origin.tv_nsec)/pow(10,9));
        curr->waitTime = curr->responseTime;
        printf("Job %d ran for: %d\n", curr->id, curr->length);
        curr->finished = true;
        jobsCompleted++;
        struct job *next;
        for (int i = 0; i < numJobs; ++i) {
            if(jobList[i].finished==false){
                next = &jobList[i];
                break;
            }
        }
        for (int i = 0; i < numJobs; ++i) {
            if(jobList[i].finished == false && jobList[i].length < next->length){
                next = &jobList[i];
            }
        }
        curr->next = next;
        curr = curr->next;
    }
    printf("End of execution with SJF.\n");
    analyze(first);
}

void rr(struct job jobList[]){
    struct timespec origin,current;
    clock_gettime(1,&origin);
    printf("Execution trace with RR:\n");
    int jobsCompleted = 0;
    struct job *first = &jobList[0];
    struct job *curr = &jobList[0];
    while(jobsCompleted < numJobs){
        if(curr->length > timeSlice){
            curr->length = curr->length - timeSlice;
            if(curr->started==false){
                curr->started=true;
                clock_gettime(1,&curr->start);
            }
            clock_gettime(1,&current);
            if(curr->wait.tv_nsec != 0) {
                curr->waitTime += (curr->wait.tv_sec + (curr->wait.tv_nsec) / pow(10, 9)) -(current.tv_sec + (current.tv_nsec) / pow(10, 9));
            }
            sleep(timeSlice);
            printf("Job %d ran for: %d\n", curr->id, timeSlice);
        }else if (curr->finished == false){
            if(curr->started==false){
                curr->started =true;
                clock_gettime(1,&curr->start);
            }
            clock_gettime(1,&current);
            if(curr->wait.tv_nsec != 0) {
                curr->waitTime +=  (current.tv_sec + (current.tv_nsec) / pow(10, 9)) - (curr->wait.tv_sec + (curr->wait.tv_nsec) / pow(10, 9));
            }
            sleep(curr->length);
            clock_gettime(1,&curr->completed);
            curr->turnaroundTime = (curr->completed.tv_sec + (curr->completed.tv_nsec)/pow(10,9)) - (origin.tv_sec + (origin.tv_nsec)/pow(10,9));
            curr->responseTime = (curr->start.tv_sec + (curr->start.tv_nsec)/pow(10,9)) - (origin.tv_sec + (origin.tv_nsec)/pow(10,9));
            printf("Job %d ran for: %d\n", curr->id, curr->length);
            curr->waitTime+=curr->responseTime;
            curr->finished = true;
            curr->length = 0;
            jobsCompleted++;
            }
        clock_gettime(1,&curr->wait);
        curr = curr->next;

    }
    printf("End of execution with RR.\n");
    analyze(first);
}

void analyze(struct job *first){
    struct job *curr = first;
    double responseTimes = 0;
    double turnaroundTimes = 0;
    double waitTimes = 0;
    printf("Begin analyzing %s:\n", policy);
    for (int i = 0; i < numJobs; ++i) {
        printf("Job %d -- Response time: %d Turnaround: %d Wait: %d\n",curr->id, (int)curr->responseTime, (int)curr->turnaroundTime, (int)curr->waitTime);
        responseTimes += (int)curr->responseTime;
        turnaroundTimes += (int)curr->turnaroundTime;
        waitTimes += (int)curr->waitTime;
        curr = curr->next;
    }
    printf("Average -- Response: %.2f Turnaround: %.2f Wait: %.2f\n",(responseTimes/numJobs),(turnaroundTimes/numJobs),(waitTimes/numJobs));
    printf("End analyzing %s.",policy);
}