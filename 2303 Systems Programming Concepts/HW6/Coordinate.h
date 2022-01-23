//
// Created by keith desantis  on 10/8/20.
//

#ifndef HW6_COORDINATE_H
#define HW6_COORDINATE_H

class Coordinate {
public:
    Coordinate();
    virtual ~Coordinate();
    void setRow(int);
    void setColumn(int);
    int getRow();
    int getColumn();
    bool isDigit(char);
    bool convertStringToPosition(Coordinate[], char[], int);
    Coordinate getTarget();
private:
    int row;
    int column;

};


#endif //HW6_COORDINATE_H
