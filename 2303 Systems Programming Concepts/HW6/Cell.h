//
// Created by keith desantis  on 10/8/20.
//

#ifndef HW6_CELL_H
#define HW6_CELL_H
#include "Coordinate.h"
#include "Watercraft.h"
#include "Variables.h"
#include "Production.h"

class Cell {
public:
    Cell();
    virtual ~Cell();
    void setSymbol(char);
    void setPosition(Coordinate);
    char getSymbol();
    Coordinate getPosition();
    Coordinate generatePosition(int, int);
    void updateGameBoard(Cell[][COLS], Coordinate);
    void randomlyPlaceShipsOnGameBoard(Cell[ROWS][COLS], Watercraft[]);
    void manuallyPlaceShipsOnGameBoard(Cell[][COLS], Watercraft[]);
    void putShipOnGameBoard(Cell[][COLS], Watercraft, Coordinate, int);
    void printGameBoard(Cell[][COLS], bool);
    void initializeGameBoard(Cell[][COLS]);
    bool isValidLocation(Cell[][COLS], Coordinate, int, int);
    short checkShot(Cell[][COLS], Coordinate);
private:
    char symbol;
    Coordinate position;

};


#endif //HW6_CELL_H
