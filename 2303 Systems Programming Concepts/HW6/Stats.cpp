//
// Created by keith desantis  on 10/8/20.
//

#include "Stats.h"

Stats::Stats()
{
    this->numHits = 0;
    this->numMisses = 0;
    this->totalShots = 0;
}

Stats::~Stats() {}

void Stats::setNumHits(int hits)
{
    this->numHits = hits;
    this->totalShots+=hits;
}

void Stats::setNumMisses(int misses)
{
    this->numMisses = misses;
    this->totalShots+=misses;
}

int Stats::getNumHits()
{
    return this->numHits;
}

int Stats::getNumMisses()
{
    return this->numMisses;
}

int Stats::getTotalShots()
{
    return this->totalShots;
}

double Stats::getHitMissRatio()
{
    return this->hitMissRatio;
}

void Stats::setHitMissRatio(double ratio)
{
    this->hitMissRatio = ratio;
}

bool Stats::isWinner(Stats players[2], int player)
{
    bool isWin = false;
    if(players[player].numHits == 17)
    {
        isWin = true;
    }
    return isWin;
}

void Stats::setTotalShots(int shots)
{
    this->totalShots = shots;
}