//
// Created by Kush Shah on 2/19/20.
//

#include "Student.h"


Student::Student(string name, int ID) {
this->ID=ID;
this->name=name;
}

Student::~Student() {

}

string Student::getName() const {
    return name;
}

int Student::getId() const {
    return ID;
}
