//
// Created by keith desantis  on 9/2/20.
// Keith DeSantis Caden Crist
//

#ifndef INC_2020HW1STARTER_HOUSE_H
#define INC_2020HW1STARTER_HOUSE_H
#include "Room.h"

typedef struct{
    int nRooms;
    Room** theRoomPs;
}House;

int getNumberOfRooms(House*);
Room* getNewRoom(House*);

#endif //INC_2020HW1STARTER_HOUSE_H
