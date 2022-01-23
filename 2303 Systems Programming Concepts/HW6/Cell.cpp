//
// Created by keith desantis  on 10/8/20.
//

#include "Cell.h"

Cell::Cell() {}

Cell::~Cell() {}

void Cell::setSymbol(char sym)
{
    this->symbol = sym;
}

void Cell::setPosition(Coordinate coor)
{
    this->position = coor;
}

char Cell::getSymbol()
{
    return this->symbol;
}

Coordinate Cell::getPosition()
{
    return this->position;
}

Coordinate Cell::generatePosition (int direction, int length) {
    Coordinate position;
    Production prod;

    if (direction == HORIZONTAL) {
        position.setRow(prod.getRandomNumber (0, ROWS));
        position.setColumn(prod.getRandomNumber (0, COLS - length));
    } else { /* VERTICAL */
        position.setRow(prod.getRandomNumber (0, ROWS - length));
        position.setColumn(prod.getRandomNumber (0, COLS));
    }

    return position;
}

void Cell::updateGameBoard (Cell gameBoard[][COLS], Coordinate target) {
    switch (gameBoard[target.getRow()][target.getColumn()].symbol) {
        /* miss */
        case WATER:
            gameBoard[target.getRow()][target.getColumn()].symbol = MISS;
            break;

            /* hit */
        case CARRIER:
        case BATTLESHIP:
        case CRUISER:
        case SUBMARINE:
        case DESTROYER:
            gameBoard[target.getRow()][target.getColumn()].symbol = HIT;
            break;

        case HIT:
        case MISS:
        default:
            break;
    }
}

void Cell::putShipOnGameBoard(Cell gameBoard[][COLS], Watercraft ship, Coordinate position, int direction)
{
    int i = ship.getLength() - 1;

    for (i = 0; i < ship.getLength(); i++) {
        if (direction == HORIZONTAL)
            gameBoard [position.getRow()][position.getColumn() + i].symbol = ship.getSymbol();
        else /* VERTICAL */
            gameBoard [position.getRow() + i][position.getColumn()].symbol = ship.getSymbol();
    }
}

bool Cell::isValidLocation(Cell gameBoard[][COLS], Coordinate position, int direction, int length)
{
    int i = length - 1;
    bool isValid = true;

    for (i = 0; isValid && i < length; i++) {
        if (direction == HORIZONTAL) {
            if (gameBoard [position.getRow()][position.getColumn() + i].symbol != WATER && (position.getColumn() + i) < COLS)
                isValid = false;
        } else { /* VERTICAL */
            if (gameBoard [position.getRow() + i][position.getColumn()].symbol != WATER && (position.getRow() + i) < ROWS)
                isValid = false;
        }
    }

    return isValid;
}

void Cell::randomlyPlaceShipsOnGameBoard(Cell gameBoard[ROWS][COLS], Watercraft ship[])
{
    Coordinate position;
    Production prod;
    int direction = -1;
    int i = 0;

    for (i = 0; i < NUM_OF_SHIPS; i++) {
        while (true) {
            direction = prod.getRandomNumber (0, 1); /* 0 -> horizontal, 1 -> vertical */
            position = generatePosition (direction, ship[i].getLength());

            if (isValidLocation (gameBoard, position, direction, ship[i].getLength())) break;
        }

        putShipOnGameBoard (gameBoard, ship[i], position, direction);
    }
}

void Cell::initializeGameBoard (Cell gameBoard[][COLS]) {
    int i = 0, j = 0;

    for (i = 0; i < ROWS; i++)
        for (j = 0; j < COLS; j++) {
            gameBoard[i][j].symbol          = WATER;
            gameBoard[i][j].position.setRow(i);
            gameBoard[i][j].position.setColumn(j);
        }
}

void Cell::printGameBoard (Cell gameBoard [][COLS], bool showPegs) {
    int i = 0, j = 0;

    printf ("  0 1 2 3 4 5 6 7 8 9\n");

    for (i = 0; i < ROWS; i++) {
        printf ("%d ", i);

        for (j = 0; j < COLS; j++) {
            if (showPegs == true)
                printf ("%c ", gameBoard [i][j].symbol);
            else {
                switch (gameBoard [i][j].symbol) {
                    case HIT:   printf ("%c ", HIT);   break;
                    case MISS:  printf ("%c ", MISS);  break;
                    case WATER:
                    default:    printf ("%c ", WATER); break;
                }
            }
        }

        putchar ('\n');
    }
}

short Cell::checkShot (Cell gameBoard[][COLS], Coordinate target) {
    int hit = -2;

    switch (gameBoard[target.getRow()][target.getColumn()].symbol) {
        /* miss */
        case WATER:
            hit = 0;
            break;

            /* hit */
        case CARRIER:
        case BATTLESHIP:
        case CRUISER:
        case SUBMARINE:
        case DESTROYER:
            hit = 1;
            break;

        case HIT:
        case MISS:
        default:
            hit = -1;
            break;
    }

    return hit;
}

void Cell::manuallyPlaceShipsOnGameBoard (Cell gameBoard[][COLS], Watercraft ship[]) {
    char       stringPosition[11] = "";
    int        i = 0, j = 0;

    Coordinate position[5];
    bool    isValid = false;

    fflush (stdin);

    for (i = 0; i < NUM_OF_SHIPS; i++) {

        while (true) {
            system ("cls");
            printGameBoard (gameBoard, true);
            printf ("> Please enter the %d cells to place the %s across (no spaces):\n", ship[i].getLength(), ship[i].getName());
            printf ("> ");
            scanf ("%s", stringPosition);

            /* convertStringtoPosition returns false if unsuccessful */
            if (position[0].convertStringToPosition (position, stringPosition, ship[i].getLength())) {

                isValid = true;

                for (j = 0; j < ship[i].getLength(); j++) {

                    if (gameBoard[position[j].getRow()][position[j].getColumn()].symbol == WATER) {
                        gameBoard[position[j].getRow()][position[j].getColumn()].symbol = ship[i].getSymbol();
                    } else {
                        isValid = false;
                        printf ("> Invalid input!\n");

                        if (j != 0)
                            while (j >= 0) {
                                gameBoard[position[j].getRow()][position[j].getColumn()].symbol = WATER;
                                j--;
                            }

                        break;
                    }
                }
            } else {
                isValid = false;
                printf ("> Invalid input!\n");
            }

            if (isValid == true) break;
        }

    }
}
