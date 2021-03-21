//
// Created by Kush Shah on 2/18/20.
//

#ifndef INC_2020HW4STARTER_ROOM_H
#define INC_2020HW4STARTER_ROOM_H


class Room {
public:
    Room();

    Room(int roomNumber, float treasure, bool searched);


    virtual ~Room();

    int roomNumber;
    float treasure;
    bool searched;
};


#endif //INC_2020HW4STARTER_ROOM_H
