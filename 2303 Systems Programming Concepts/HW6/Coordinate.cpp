//
// Created by keith desantis  on 10/8/20.
//

#include <iostream>
#include "Coordinate.h"

Coordinate::Coordinate() {}

Coordinate::~Coordinate() {}

void Coordinate::setColumn(int col)
{
    this->column = col;
}

void Coordinate::setRow(int row)
{
    this->row = row;
}

int Coordinate::getRow()
{
    return this->row;
}

int Coordinate::getColumn()
{
    return this->column;
}

bool Coordinate::convertStringToPosition(Coordinate position[], char stringPosition[] , int length)
{
    bool flag = true;
    int i = 0, j = 0, k = 1;
    /* loops through the length of the ship */
    for (i = 0; i < length && flag; i++) {
        /* checks if each cell is a digit */
        if (position[0].isDigit(stringPosition[j]) && position[0].isDigit(stringPosition[k])) {
            position[i].setRow(stringPosition[j] - '0');
            position[i].setColumn(stringPosition[k] - '0');

            j += 2;
            k += 2;
        } else {
            flag = false;
        }
    }

    return flag;
}

bool Coordinate::isDigit(char ch)
{
    if(ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch == '9' || ch == '0')
    {
        return true;
    }

    return false;
}

Coordinate Coordinate::getTarget()
{
    Coordinate target;

    fflush (stdin);
    printf ("> Enter Target (ex. > 3 4):\n");
    printf ("> ");
    scanf ("%d %d", &target.row, &target.column);

    return target;
}