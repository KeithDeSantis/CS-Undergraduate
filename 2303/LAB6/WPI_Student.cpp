//
// Created by Kush Shah on 2/26/20.
//

#include "WPI_Student.h"

WPI_Student::WPI_Student(string name, int ID) : Student(name) {
    setName(name);
    this->ID=ID;
}
int WPI_Student::getID() {
    return ID;
}

void WPI_Student::setID(int ID) {
this->ID = ID;
}
