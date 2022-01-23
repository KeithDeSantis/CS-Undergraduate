//
// Created by keith desantis  on 10/8/20.
//

#ifndef HW6_STATS_H
#define HW6_STATS_H


class Stats {
public:
    Stats();
    virtual ~Stats();
    void setNumHits(int);
    void setNumMisses(int);
    void setHitMissRatio(double);
    void setTotalShots(int);
    int getNumHits();
    int getNumMisses();
    int getTotalShots();
    double getHitMissRatio();
    bool isWinner(Stats[2], int);
private:
    int numHits;
    int numMisses;
    int totalShots;
    double hitMissRatio;


};


#endif //HW6_STATS_H
