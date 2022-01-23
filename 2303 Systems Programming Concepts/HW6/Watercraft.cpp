//
// Created by keith desantis  on 10/8/20.
//

#include "Watercraft.h"

Watercraft::Watercraft() {}

Watercraft::~Watercraft() {}

void Watercraft::setSymbol(char sym)
{
    this->symbol = sym;
}

void Watercraft::setLength(short len)
{
    this->length = len;
}

void Watercraft::setName(char* name)
{
    this->name = name;
}

char Watercraft::getSymbol()
{
    return this->symbol;
}

short Watercraft::getLength()
{
    return this->length;
}

char* Watercraft::getName()
{
    return this->name;
}

bool Watercraft::checkSunkShip(short sunkShip[2][NUM_OF_SHIPS], short player, char shipSymbol, FILE* stream)
{
    bool sunked = false;
    switch(shipSymbol)
    {
        case 'c':
            if(--sunkShip[player][0] == 0) //Checks if, after this hit, the ship will be sunk
            {
                printf("> Player %d's Carrier sunked!\n", player+1);
                fprintf(stream, "Player %d's Carrier sunked!\n", player+1);
                sunked = true;
            }
            break;
        case 'b':
            if(--sunkShip[player][1] == 0)
            {
                printf("> Player %d's Battleship sunked!\n", player+1);
                fprintf(stream, "Player %d's Battleship sunked!\n", player+1);
                sunked = true;
            }
            break;
        case 'r':
            if(--sunkShip[player][2] == 0)
            {
                printf("> Player %d's Cruiser sunked!\n", player+1);
                fprintf(stream, "Player %d's Cruiser sunked!\n", player+1);
                sunked = true;
            }
            break;
        case 's':
            if(--sunkShip[player][3] == 0)
            {
                printf("> Player %d's Submarine sunked!\n", player+1);
                fprintf(stream, "Player %d's Submarine sunked!\n", player+1);
                sunked = true;
            }
            break;
        case 'd':
            if(--sunkShip[player][4] == 0)
            {
                printf("> Player %d's Destroyer sunked!\n", player+1);
                fprintf(stream, "Player %d's Destroyer sunked!\n", player+1);
                sunked = true;
            }
            break;
    }
    return sunked;
}