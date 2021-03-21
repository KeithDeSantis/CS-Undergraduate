//
// Created by Kush Shah on 2/19/20.
//

#ifndef LAB5_STUDENT_H
#define LAB5_STUDENT_H
#include <string>
using namespace std;
class Student {
public:
    Student();

    Student(string name, int ID);

    virtual ~Student();

    string getName() const;

    int getId() const;

public:
    string name;
    int ID;
};


#endif //LAB5_STUDENT_H
