//
// Created by Kush Shah on 2/26/20.
//

#include "Student.h"

Student::Student(string name){
this->name = name;
}

Student::~Student() {

}


string Student::getName() {
    return this->name;
}

void Student::setName(string name) {
this->name=name;
}
