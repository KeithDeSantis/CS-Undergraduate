#include <iostream>
#include "Room.h"

int main() {
    std::cout << "Goodbye World!" << std::endl;
    Room* r = new Room(4);
    printf("There are %d doors in this room",r->getNumDoors());
    return 0;

}
