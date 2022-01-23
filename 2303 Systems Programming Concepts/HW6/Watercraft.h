//
// Created by keith desantis  on 10/8/20.
//

#ifndef HW6_WATERCRAFT_H
#define HW6_WATERCRAFT_H

#include "Variables.h"
#include <string>
using namespace std;
#include "stdio.h"

class Watercraft {
public:
    Watercraft();
    virtual ~Watercraft();
    void setSymbol(char);
    void setLength(short);
    void setName(char*);
    char getSymbol();
    short getLength();
    char* getName();
    bool checkSunkShip(short[][5], short, char, FILE*);
private:
    char symbol;
    short length;
    char* name;
};


#endif //HW6_WATERCRAFT_H
