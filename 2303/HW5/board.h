//
// Created by 14132 on 3/1/2020.//

#ifndef HW5_BOARD_H
#define HW5_BOARD_H

#include <stdio.h>
#include "checker.h"
#include "LinkedList.h"
class board {
public:
    board();
    char boardArray[8][8]{{' '}};

    checker* o0 = new checker(0,1,'O');
    checker* o1 = new checker(0,3,'O');
    checker* o2 = new checker(0,5,'O');
    checker* o3 = new checker(0,7,'O');
    checker* o4 = new checker(1,0,'O');
    checker* o5 = new checker(1,2,'O');
    checker* o6 = new checker(1,4,'O');
    checker* o7 = new checker(1,6,'O');
    checker* o8 = new checker(2,1,'O');
    checker* o9 = new checker(2,3,'O');
    checker* o10 = new checker(2,5,'O');
    checker* o11 = new checker(2,7,'O');

    checker* x0 = new checker(7,0,'X');
    checker* x1 = new checker(7,2,'X');
    checker* x2 = new checker(7,4,'X');
    checker* x3 = new checker(7,6,'X');
    checker* x4 = new checker(6,1,'X');
    checker* x5 = new checker(6,3,'X');
    checker* x6 = new checker(6,5,'X');
    checker* x7 = new checker(6,7,'X');
    checker* x8 = new checker(5,0,'X');
    checker* x9 = new checker(5,2,'X');
    checker* x10 = new checker(5,4,'X');
    checker* x11 = new checker(5,6,'X');

    LinkedList* Opieces = Opieces->makeEmptyLinkedList();
    LinkedList* Xpieces = Xpieces->makeEmptyLinkedList();

    checker* opieces[12] = {o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11};
    checker* xpieces[12] = {x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11};




    FILE* log = fopen("log.txt","w+");
    int place[4] = {-666,-666,-666,-666};

    void printBoard();

    void move();

    int tryToMove = 10;

    bool onGoingTurn= true;

    bool turn = true; //true when X turn


    bool canJump(checker*);

    int canMove(checker*);

    virtual ~board();

    void updateLog(int Prow, int Pcol, int Nrow, int Ncol);

    void checkKing();

    void resetMoveCount();

    checker *canMove(char team);

    void makeMoveX(checker *cc);

    void makeMoveO(checker *cc);

    void makeMove(checker *c);
};


#endif //HW5_BOARD_H
