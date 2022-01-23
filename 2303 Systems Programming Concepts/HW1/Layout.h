//
// Created by keith desantis  on 9/2/20.
// Keith DeSantis Caden Crist
//

#ifndef INC_2020HW1STARTER_LAYOUT_H
#define INC_2020HW1STARTER_LAYOUT_H
#include "Room.h"

typedef struct{
    Room theRooms[20];
}Layout;

int countRooms(Layout*);
Room* getFirstRoom(Layout*);

#endif //INC_2020HW1STARTER_LAYOUT_H
