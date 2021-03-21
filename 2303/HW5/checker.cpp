//
// Created by 14132 on 3/1/2020.//

#include "checker.h"

checker::checker(int row, int col, char team){
    this->row=row;
    this->col=col;
    this->team=team;
    this->king= false;
}

checker::~checker() {

}

void checker::promote() {
    this->king = true;
}

void checker::setRow(int row) {
    checker::row = row;
}

void checker::setCol(int col) {
    checker::col = col;
}



