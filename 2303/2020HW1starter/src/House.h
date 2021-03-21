#ifndef HOUSE_H_
#define HOUSE_H_
#include <stdio.h>
#include <stdbool.h>
#include <string.h>//strncpy
#include <stdlib.h>//strtol
#include "Layout.h"
//Name: Kush Shah kshah2@wpi.edu

typedef struct{
    int nRooms;
    room* aRoom;
} house;

int countRooms(layout*);
room* getFirstRoom(layout*);





#endif