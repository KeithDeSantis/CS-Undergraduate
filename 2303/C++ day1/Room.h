//
// Created by Kush Shah on 2/10/20.
//

#ifndef C___DAY1_ROOM_H
#define C___DAY1_ROOM_H


#include <ostream>

class Room {
public:
    Room(int numDoors);

    virtual ~Room();
    int getNumDoors();
};


#endif //C___DAY1_ROOM_H
