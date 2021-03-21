
#ifndef LAYOUT_H_
#define LAYOUT_H_
#include <stdio.h>
#include <stdbool.h>
#include <string.h>//strncpy
#include <stdlib.h>//strtol
#include "Room.h"
//Name: Kush Shah kshah2@wpi.edu


typedef struct{
    int nRooms;
    room* firstRoom;
} layout;


bool open(room*);

#endif