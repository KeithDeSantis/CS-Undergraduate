//
// Created by Kush Shah on 2/10/20.
//

#include "Room.h"
int numDoors;

Room::Room(int Doors) {
    numDoors = Doors;
}

int Room::getNumDoors() {
    return numDoors;;
}

Room::~Room() = default;

