//
// Created by kushshah on 2/12/21.
//

#ifndef PROJECT2_STAGE_H
#define PROJECT2_STAGE_H
#include <stdio.h>
#include <stdlib.h>
#include "pthread.h"
#include <time.h>
#include <unistd.h>
#include <math.h>
#define EMPTY 0
#define DANCER 1
#define JUGGLER 2
#define SOLOIST 3

void printStage(int *stage);
void *dancer();
void *juggler();
void *soloist();
int howMany(int me);
int anyOthers(int me);
int emptyStageChecker();
#endif //PROJECT2_STAGE_H
