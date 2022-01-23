//
// Created by keith desantis  on 9/2/20.
// Keith DeSantis Caden Crist
//

#ifndef INC_2020HW1STARTER_ROOM_H
#define INC_2020HW1STARTER_ROOM_H
#include "stdbool.h"

typedef struct{
    bool hasTreasure;
    bool isOpen;
}Room;

bool isOpen(Room*);
bool hasTreasure(Room*);

#endif //INC_2020HW1STARTER_ROOM_H
