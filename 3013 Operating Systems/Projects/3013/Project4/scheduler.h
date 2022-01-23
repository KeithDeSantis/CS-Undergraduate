//
// Created by kushshah on 3/5/21.
//

#ifndef PROJECT4_SCHEDULER_H
#define PROJECT4_SCHEDULER_H

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdbool.h>
#include <time.h>
#include <math.h>
#include <string.h>


struct job {
    int id ;
    int length ;

    double responseTime;
    double turnaroundTime;
    double waitTime;
    bool started;
    bool finished;
    struct timespec start, completed, wait;
// other meta data
    struct job * next ;
};

void fifo(struct job jobList[]);
void sjf(struct job jobList[]);
void rr(struct job jobList[]);
void analyze(struct job *first);
#endif //PROJECT4_SCHEDULER_H
