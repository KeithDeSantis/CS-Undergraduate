/*
 * Tests.cpp
 *
 *  Created on: Feb 1, 2020
 *      Author: Therese
 */

#include "Tests.h"
#include "Stats.h"
#include "Coordinate.h"
#include "Cell.h"
#include "Production.h"
#include <iostream>

Tests::Tests() {
	// TODO Auto-generated constructor stub

}

Tests::~Tests() {
	// TODO Auto-generated destructor stub
}

bool Tests::tests()
{
    bool ok1 = this->testSetNumHits();
    bool ok2 = this->testSetNumMisses();
    bool ok3 = this->testGetNumHits();
    bool ok4 = this->testGetNumMisses();
    bool ok5 = this->testSetRow();
    bool ok6 = this->testSetColumn();
    bool ok7 = this->testGetRow();
    bool ok8 = this->testGetColumn();
    bool ok9 = this->testSetSymbol();
    bool ok10 = this->testSetPosition();
    bool ok11 = this->testGetSymbol();
    bool ok12 = this->testGetPosition();
    bool ok13 = this->testSetSymbolWatercraft();
    bool ok14 = this->testSetLength();
    bool ok15 = this->testSetName();
    bool ok16 = this->testGetSymbolWatercraft();
    bool ok17 = this->testGetLength();
    bool ok18 = this->testGetName();
    bool ok19 = this->testCheckSunkedShip();
    bool ok20 = this->testGetTotalShots();
    bool ok21 = this->testGetHitMissRatio();
    bool ok22 = this->testIsWinner();
    bool ok23 = this->testIsDigit();
    bool ok24 = this->testConvertStringToPosition();
    bool ok25 = this->testCheckBoundsOfCardinal();
    bool ok26 = this->testGeneratePosition();
    bool ok27 = this->testPutShipOnBoard();
    bool ok28 = this->testRandomlyPutShipsOnGameBoard();
    bool ok29 = this->testInitializeGameBoard();
    bool ok30 = this->testCheckShot();
	bool answer = ok1 && ok2 && ok3 && ok4 && ok5 && ok6 && ok7 && ok8 && ok9 && ok10 && ok11 && ok12 && ok13 && ok14 && ok15 && ok16 && ok17 && ok18 && ok19 && ok20
	        && ok21 && ok22 && ok23 && ok24 && ok25 && ok26 && ok27 && ok28 && ok29 && ok30;
	return answer;
}

bool Tests::testSetNumHits()
{
    std::cout << "Starting Test Set Num Hits" << std::endl;
    Stats* stat = new Stats();
    stat->setNumHits(3);
    bool answer = stat->getNumHits() == 3;
    if(answer) { std::cout << "Test Set Num Hits Passed!" << std::endl; }
    else { std::cout << "Test Set Num Hits Failed." << std::endl; }
    return answer;
}

bool Tests::testSetNumMisses()
{
    std::cout << "Starting Test Set Num Misses" << std::endl;
    Stats* stat = new Stats();
    stat->setNumMisses(3);
    bool answer = stat->getNumMisses() == 3;
    if(answer) { std::cout << "Test Set Num Misses Passed!" << std::endl; }
    else { std::cout << "Test Set Num Misses Failed." << std::endl; }
    return answer;
}

bool Tests::testGetNumMisses()
{
    std::cout << "Starting Test Get Num Misses" << std::endl;
    Stats* stats = new Stats;
    stats->setNumMisses(5);
    bool answer = stats->getNumMisses() == 5;
    if(answer) { std::cout << "Test Get Num Misses Passed!" << std::endl; }
    else { std::cout << "Test Get Num Misses Failed." << std::endl; }
    return answer;
}

bool Tests::testGetNumHits()
{
    std::cout << "Starting Test Get Num Hits" << std::endl;
    Stats* stats = new Stats;
    stats->setNumHits(5);
    bool answer = stats->getNumHits() == 5;
    if(answer) { std::cout << "Test Get Num Hits Passed!" << std::endl; }
    else { std::cout << "Test Get Num Hits Failed." << std::endl; }
    return answer;
}

bool Tests::testSetRow()
{
    std::cout << "Starting Test Set Row" << std::endl;
    Coordinate* coor = new Coordinate();
    coor->setRow(4);
    bool answer = coor->getRow() == 4;
    if(answer) { std::cout << "Test Set Row Passed!" << std::endl; }
    else { std::cout << "Test Set Row Failed." << std::endl; }
    return answer;
}

bool Tests::testSetColumn()
{
    std::cout << "Starting Test Set Column" << std::endl;
    Coordinate* coor = new Coordinate();
    coor->setColumn(4);
    bool answer = coor->getColumn() == 4;
    if(answer) { std::cout << "Test Set Column Passed!" << std::endl; }
    else { std::cout << "Test Set Column Failed." << std::endl; }
    return answer;
}

bool Tests::testGetRow()
{
    std::cout << "Starting Test Get Row" << std::endl;
    Coordinate* coor = new Coordinate();
    coor->setRow(4);
    bool answer = coor->getRow() == 4;
    if(answer) { std::cout << "Test Get Row Passed!" << std::endl; }
    else { std::cout << "Test Get Row Failed." << std::endl; }
    return answer;
}

bool Tests::testGetColumn()
{
    std::cout << "Starting Test Get Column" << std::endl;
    Coordinate* coor = new Coordinate();
    coor->setColumn(4);
    bool answer = coor->getColumn() == 4;
    if(answer) { std::cout << "Test Get Column Passed!" << std::endl; }
    else { std::cout << "Test Get Column Failed." << std::endl; }
    return answer;
}

bool Tests::testSetSymbol()
{
    std::cout << "Starting Test Set Symbol" << std::endl;
    Cell* cell = new Cell();
    cell->setSymbol('g');
    bool answer = cell->getSymbol() == 'g';
    if(answer) { std::cout << "Test Set Symbol Passed!" << std::endl; }
    else { std::cout << "Test Set Symbol Failed." << std::endl; }
    return answer;
}

bool Tests::testSetPosition()
{
    std::cout << "Starting Test Set Position" << std::endl;
    Cell* cell = new Cell();
    Coordinate* coor = new Coordinate();
    coor->setColumn(4);
    coor->setRow(4);
    cell->setPosition(*coor);
    bool answer = cell->getPosition().getColumn() == 4 && cell->getPosition().getRow() == 4;
    if(answer) { std::cout << "Test Set Position Passed!" << std::endl; }
    else { std::cout << "Test Set Position Failed." << std::endl; }
    return answer;
}

bool Tests::testGetSymbol()
{
    std::cout << "Starting Test Get Symbol" << std::endl;
    Cell* cell = new Cell();
    cell->setSymbol('g');
    bool answer = cell->getSymbol() == 'g';
    if(answer) { std::cout << "Test Get Symbol Passed!" << std::endl; }
    else { std::cout << "Test Get Symbol Failed." << std::endl; }
    return answer;
}

bool Tests::testGetPosition()
{
    std::cout << "Starting Test Get Position" << std::endl;
    Cell* cell = new Cell();
    Coordinate* coor = new Coordinate();
    coor->setColumn(4);
    coor->setRow(4);
    cell->setPosition(*coor);
    bool answer = cell->getPosition().getColumn() == 4 && cell->getPosition().getRow() == 4;
    if(answer) { std::cout << "Test Get Position Passed!" << std::endl; }
    else { std::cout << "Test Get Position Failed." << std::endl; }
    return answer;
}

bool Tests::testSetSymbolWatercraft()
{
    std::cout << "Starting Test Set Symbol Watercraft" << std::endl;
    Watercraft* wc = new Watercraft();
    wc->setSymbol('g');
    bool answer = 'g' == wc->getSymbol();
    if(answer) { std::cout << "Test Set Symbol Watercraft Passed!" << std::endl; }
    else { std::cout << "Test Set Symbol Watercraft Failed." << std::endl; }
    return answer;
}

bool Tests::testSetLength()
{
    std::cout << "Starting Test Set Length" << std::endl;
    Watercraft* wc = new Watercraft();
    wc->setLength(2);
    bool answer = 2 == wc->getLength();
    if(answer) { std::cout << "Test Set Length Passed!" << std::endl; }
    else { std::cout << "Test Set Length Failed." << std::endl; }
    return answer;
}

bool Tests::testSetName()
{
    std::cout << "Starting Test Set Name" << std::endl;
    Watercraft* wc = new Watercraft();
    char* name = "bruh";
    wc->setName(name);
    bool answer = wc->getName() == name;
    if(answer) { std::cout << "Test Set Name Passed!" << std::endl; }
    else { std::cout << "Test Set Name Failed." << std::endl; }
    return answer;
}

bool Tests::testGetSymbolWatercraft()
{
    std::cout << "Starting Test Get Symbol Watercraft" << std::endl;
    Watercraft* wc = new Watercraft();
    wc->setSymbol('g');
    bool answer = 'g' == wc->getSymbol();
    if(answer) { std::cout << "Test Get Symbol Watercraft Passed!" << std::endl; }
    else { std::cout << "Test Get Symbol Watercraft Failed." << std::endl; }
    return answer;
}

bool Tests::testGetLength()
{
    std::cout << "Starting Test Get Length" << std::endl;
    Watercraft* wc = new Watercraft();
    wc->setLength(2);
    bool answer = 2 == wc->getLength();
    if(answer) { std::cout << "Test Get Length Passed!" << std::endl; }
    else { std::cout << "Test Get Length Failed." << std::endl; }
    return answer;
}

bool Tests::testGetName()
{
    std::cout << "Starting Test Get Name" << std::endl;
    Watercraft* wc = new Watercraft();
    char* name = "bruh";
    wc->setName(name);
    bool answer = wc->getName() == name;
    if(answer) { std::cout << "Test Get Name Passed!" << std::endl; }
    else { std::cout << "Test Get Name Failed." << std::endl; }
    return answer;
}

bool Tests::testCheckSunkedShip()
{
    std::cout << "Starting Test Check Sunked Ship" << std::endl;
    Watercraft* wc = new Watercraft();
    FILE* f = fopen("test.txt", "w+");
    short arrayOfShips[2][5];
    for(int i = 0; i<2; i++)
    {
        for(int j = 0; j<5; j++)
        {
            arrayOfShips[i][j] = 2;
        }
    }
    arrayOfShips[0][0] = 1;
    bool answer1 = wc->checkSunkShip(arrayOfShips, 0, 'c', f);
    bool answer2 = !wc->checkSunkShip(arrayOfShips, 1, 'c', f);
    bool answer = answer1 && answer2;
    if(answer) { std::cout << "Test Check Sunked Ship Passed!" << std::endl; }
    else { std::cout << "Test Check Sunked ShipFailed." << std::endl; }
    fclose(f);
    return answer;

}

bool Tests::testGetTotalShots()
{
    std::cout << "Starting Test Get Total Shots" << std::endl;
    Stats* stats = new Stats();
    stats->setNumMisses(1);
    bool answer = stats->getTotalShots() == 1;
    if(answer) { std::cout << "Test Get Total Shots Passed!" << std::endl; }
    else { std::cout << "Test Get Total Shots Failed." << std::endl; }
    return answer;
}

bool Tests::testGetHitMissRatio()
{
    std::cout << "Starting Test Get Hit Miss Ratio" << std::endl;
    Stats* stats = new Stats();
    stats->setHitMissRatio(0.4);
    bool answer = stats->getHitMissRatio() == 0.4;
    if(answer) { std::cout << "Test Get Hit Miss Ratio Passed!" << std::endl; }
    else { std::cout << "Test Get Hit Miss Ratio Failed." << std::endl; }
    return answer;
}

bool Tests::testIsWinner()
{
    std::cout << "Starting Test Is Winner" << std::endl;
    Stats* p1stats = new Stats();
    Stats* p2stats = new Stats();
    p2stats->setNumHits(17);
    Stats playerStats[2];
    playerStats[0] = *p1stats;
    playerStats[1] = *p2stats;
    bool answer = !p1stats->isWinner(playerStats, 0) && p1stats->isWinner(playerStats, 1);
    if(answer) { std::cout << "Test Is Winner Passed!" << std::endl; }
    else { std::cout << "Test Is Winner Failed." << std::endl; }
    return answer;
}

bool Tests::testIsDigit()
{
    std::cout << "Starting Test Is Digit" << std::endl;
    Coordinate* coord = new Coordinate();
    bool answer = coord->isDigit('5') && !coord->isDigit('g');
    if(answer) { std::cout << "Test Is Digit Passed!" << std::endl; }
    else { std::cout << "Test Is Digit Failed." << std::endl; }
    return answer;
}


bool Tests::testConvertStringToPosition()
{
    std::cout << "Starting Test Convert String To Position" << std::endl;
    Coordinate* coordinate = new Coordinate();
    Coordinate coord[2];
    char stringPosition[4];
    stringPosition[0] = '2';
    stringPosition[1] = '3';
    stringPosition[2] = '1';
    stringPosition[3] = '4';
    bool answer = coordinate->convertStringToPosition(coord, stringPosition, 2);
    if(answer) { std::cout << "Test Convert String To Position Passed!" << std::endl; }
    else { std::cout << "Test Convert String To Position Failed." << std::endl; }
    return answer;
}

bool Tests::testCheckBoundsOfCardinal()
{
    std::cout << "Starting Test Check Bounds of Cardinal" << std::endl;
    Production* prod = new Production();
    bool cardinals[4];
    prod->checkBoundsOfCardinal(cardinals, -1, 0);
    bool answer;
    if(cardinals[0])
    { answer = false; }
    else
        { answer = true; }
    if(answer) { std::cout << "Test Test Check Bounds of Cardinal Passed!" << std::endl; }
    else { std::cout << "Test Check Bounds of Cardinal Failed." << std::endl; }
    return answer;
}

bool Tests::testGeneratePosition()
{
    std::cout << "Starting Test Generate Position" << std::endl;
    Cell cell;
    cell.generatePosition(NORTH, 5);
    bool answer = true;
    if(answer) { std::cout << "Test Generate Position Passed!" << std::endl; }
    else { std::cout << "Test Generate Position Failed." << std::endl; }
    return answer;
}

bool Tests::testPutShipOnBoard()
{
    std::cout << "Starting Test Put Ship On Board" << std::endl;
    Cell gameBoard[ROWS][COLS];
    Cell cell1;
    Watercraft* ship = new Watercraft();
    ship->setSymbol('r');
    ship->setLength(3);
    Coordinate* coord = new Coordinate();
    coord->setColumn(5);
    coord->setRow(5);
    cell1.putShipOnGameBoard(gameBoard, *ship, *coord, HORIZONTAL);
    bool answer = gameBoard[5][5].getSymbol() == 'r';
    if(answer) { std::cout << "Test Put Ship On Board Passed!" << std::endl; }
    else { std::cout << "Test Put Ship On Board Failed." << std::endl; }
    return answer;
}

bool Tests::testRandomlyPutShipsOnGameBoard()
{
    std::cout << "Starting Test Randomly Put Ships On Game Board" << std::endl;
    Cell gameBoard[ROWS][COLS];
    Cell cell1;
    cell1.initializeGameBoard(gameBoard);
    Watercraft ship[NUM_OF_SHIPS];
    for(int i = 0; i < NUM_OF_SHIPS; i++)
    {
        switch(i)
        {
            case 0:
                ship[0].setLength(5);
                ship[0].setSymbol('c');
                ship[0].setName("Carrier");
                break;
            case 1:
                ship[1].setLength(4);
                ship[1].setSymbol('b');
                ship[1].setName("Battleship");
                break;
            case 2:
                ship[2].setLength(3);
                ship[2].setSymbol('r');
                ship[2].setName("Cruiser");
                break;
            case 3:
                ship[3].setLength(3);
                ship[3].setSymbol('s');
                ship[3].setName("Submarine");
                break;
            case 4:
                ship[4].setLength(2);
                ship[4].setSymbol('d');
                ship[4].setName("Destroyer");
                break;
        }
    }
    cell1.randomlyPlaceShipsOnGameBoard(gameBoard, ship);
    std::cout << "Test Randomly Put Ships On Game Board Passed!" << std::endl;
    return true;
}

bool Tests::testInitializeGameBoard()
{
    std::cout << "Starting Test Initialize Game Board" << std::endl;
    Cell gameBoard[ROWS][COLS];
    Cell cell1;
    cell1.initializeGameBoard(gameBoard);
    bool answer = true;
    for(int i = 0; i<ROWS; i++)
    {
        for(int j = 0; j<COLS; j++)
        {
            answer = gameBoard[i][j].getSymbol() == '~';
        }
    }
    if(answer) { std::cout << "Test Initialize Game Board Passed!" << std::endl; }
    else { std::cout << "Test Initialize Game Board Failed." << std::endl; }
    return answer;
}

bool Tests::testCheckShot()
{
    std::cout << "Starting Test Check Shot" << std::endl;
    Cell gameBoard[ROWS][COLS];
    Cell cell1;
    cell1.initializeGameBoard(gameBoard);
    Coordinate* position1 = new Coordinate();
    position1->setColumn(4);
    position1->setRow(4);
    bool answer1 = cell1.checkShot(gameBoard, *position1) == 0;
    gameBoard[4][4].setSymbol('c');
    bool answer2 = cell1.checkShot(gameBoard, *position1) == 1;
    bool answer = answer1 && answer2;
    if(answer) { std::cout << "Test Check Shot Passed!" << std::endl; }
    else { std::cout << "Test Check Shot Failed." << std::endl; }
    return answer;
}
