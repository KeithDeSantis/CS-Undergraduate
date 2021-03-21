#ifndef SEARCH_H_
#define SEARCH_H_
#include <stdio.h>
#include <stdbool.h>
#include <string.h>//strncpy
#include <stdlib.h>//strtol
#include "House.h"
#include "Room.h"
//Name: Kush Shah kshah2@wpi.edu

int getNumberOfRooms(house*);
room* getNewRoom(house*);
bool haveTreasure(room*);

#endif