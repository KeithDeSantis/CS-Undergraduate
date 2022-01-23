//
// Created by kushshah on 2/16/21.
//

#ifndef PROJECT2_FEDOOPS_H
#define PROJECT2_FEDOOPS_H

#include "semaphore.h"
#include "pthread.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <math.h>
#include <stdbool.h>

#define PACKAGES 15
#define UNTOUCHED -1
#define TAKEN 0
#define SCALE 1
#define BARCODE 2
#define SHAKER 3
#define XRAY 4
#define TRUCK 5



struct package{
    int ID;
    int status;
    int instructions[4];
};

struct package ppp[PACKAGES];


//{SCALE, BARCODE, SHAKER, XRAY} will contain package struct;

void initPackage(struct package *p, int i);
bool inArray(int a[], int b);
bool willDeadlock(struct package *p);
bool allStationsFree();
sem_t* getInstructionSem(struct package *p);
void printInstructions(int *instructions);
void greenTeam(int me);
void redTeam(int me);
void yellowTeam(int me);
void blueTeam(int me);
int howManyLeft();
void printStations();

sem_t greenSem;
sem_t redSem;
sem_t yellowSem;
sem_t blueSem;

sem_t stationSem;

sem_t scale;
sem_t barcode;
sem_t shaker;
sem_t xray;
sem_t pppSem;
sem_t choosing;
struct package EMPTY;
struct package station[4];

#endif //PROJECT2_FEDOOPS_H
