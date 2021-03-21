//
// Created by 14132 on 3/1/2020.//

#include "Production.h"

Production::Production() {}

Production::~Production() {}

bool Production::prod(int argc, char* argv[]) {
    board* b = new board();

    for(int i = 0; i <100; i++){
        b->printBoard();
        printf("%d\n",i);
        b->move();
        b->resetMoveCount();
    }
    b->printBoard();

}