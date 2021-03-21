//
// Created by Kush Shah on 2/26/20.
//

#ifndef LAB6_STUDENT_H
#define LAB6_STUDENT_H
#include <string>
using namespace std;

class Student {
public:
    Student(string name);
    string getName();
    void setName(string);
    virtual ~Student();

private:
    string name;
};



#endif //LAB6_STUDENT_H
