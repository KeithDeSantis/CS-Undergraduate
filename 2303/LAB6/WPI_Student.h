//
// Created by Kush Shah on 2/26/20.
//

#ifndef LAB6_WPI_STUDENT_H
#define LAB6_WPI_STUDENT_H


#include "cmake-build-debug/Student.h"

class WPI_Student: public Student {
public:


    WPI_Student(string name, int ID);

    int getID();
    void setID(int);
private:
    int ID;
};


#endif //LAB6_WPI_STUDENT_H
