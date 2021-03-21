
//
// Created by 14132 on 3/1/2020.
//

#include <cstdio>
#include "board.h"
#include <random>

board::board() {
    char boardStart[64] = {'.', 'O', '.', 'O', '.', 'O', '.', 'O',
                           'O', '.', 'O', '.', 'O', '.', 'O', '.',
                           '.', 'O', '.', 'O', '.', 'O', '.', 'O',
                           '.', '.', '.', '.', '.', '.', '.', '.',
                           '.', '.', '.', '.', '.', '.', '.', '.',
                           'X', '.', 'X', '.', 'X', '.', 'X', '.',
                           '.', 'X', '.', 'X', '.', 'X', '.', 'X',
                           'X', '.', 'X', '.', 'X', '.', 'X', '.'};
    int count = 0;
    for (auto &i : boardArray) {
        for (char &j : i) {
            j = boardStart[count];
            count++;
        }
    }
    for (int i = 0; i < 12; i++) {
        Opieces->savePayload(Opieces, opieces[i]);
        Xpieces->savePayload(Xpieces, xpieces[i]);
    }
    printf("X-size: %d\n", Xpieces->getSize(Xpieces));
    printf("O-size: %d\n", Opieces->getSize(Opieces));

}

void board::moveKing() {
    while (onGoingTurn) {
        if (turn) {
            int i = random() % (Xpieces->getSize(Xpieces) - 1);
            checker *king = Xpieces->get(Xpieces, i);
        }
    }
}

void board::move() {
    while (onGoingTurn) {
        printf("%d\n", turn);
        checkKing();

        if (turn) {
            int i = random() % (Xpieces->getSize(Xpieces) - 1);
            int d = random() % 2 + 1;
            for (int k = 0; k < Xpieces->getSize(Xpieces); k++) {
                if (Xpieces->get(Xpieces, k) != nullptr) {
                    bool ean = canJump(Xpieces->get(Xpieces, k));
                    if (ean) {
                        i = k;
                    }
                }
            }


            if (place[0] != -666 && Xpieces->get(Xpieces, i) != nullptr) {
                int Prow = Xpieces->get(Xpieces, i)->row;
                int Pcol = Xpieces->get(Xpieces, i)->col;
                boardArray[Xpieces->get(Xpieces, i)->row][Xpieces->get(Xpieces, i)->col] = '.';
                Xpieces->get(Xpieces, i)->row = place[0];
                Xpieces->get(Xpieces, i)->col = place[1];
                int Nrow = Xpieces->get(Xpieces, i)->row;
                int Ncol = Xpieces->get(Xpieces, i)->col;
                boardArray[place[0]][place[1]] = 'X';
                boardArray[place[2]][place[3]] = '.';
                for (int j = 0; j < Xpieces->getSize(Xpieces); j++) {
                    if (Opieces->get(Opieces, j)->row == place[2] && Opieces->get(Opieces, j)->col == place[3]) {
                        Opieces->removeFromList(Opieces, Opieces->get(Opieces, j));
                    }
                }
                updateLog(Prow, Pcol, Nrow, Ncol);
                puts("JUMPED X");
                turn = !turn;
                onGoingTurn = !onGoingTurn;
            } else {
                int direction = canMove(Xpieces->get(Xpieces, i));
                while (direction == 0) {
                    if (tryToMove <= 0) {
                        turn = !turn;
                        onGoingTurn = !onGoingTurn;
                    }
                    i = random() % (Xpieces->getSize(Xpieces) - 1);
                    direction = canMove(Xpieces->get(Xpieces, i));
                    tryToMove--;
                }
                if (direction == 3) {
                    if (d == 1 && Xpieces->get(Xpieces, i) != nullptr) {
                        int Prow = Xpieces->get(Xpieces, i)->row;
                        int Pcol = Xpieces->get(Xpieces, i)->col;
                        boardArray[Xpieces->get(Xpieces, i)->row][Xpieces->get(Xpieces, i)->col] = '.';
                        Xpieces->get(Xpieces, i)->row = Xpieces->get(Xpieces, i)->row - 1;
                        Xpieces->get(Xpieces, i)->col = Xpieces->get(Xpieces, i)->col - 1;
                        int Nrow = Xpieces->get(Xpieces, i)->row;
                        int Ncol = Xpieces->get(Xpieces, i)->col;
                        boardArray[Xpieces->get(Xpieces, i)->row][Xpieces->get(Xpieces, i)->col] = 'X';
                        updateLog(Prow, Pcol, Nrow, Ncol);
                        puts("MOVED LEFT RANDOM X");
                        turn = !turn;
                        onGoingTurn = !onGoingTurn;
                    } else if (d == 2 && Xpieces->get(Xpieces, i) != nullptr) {
                        int Prow = Xpieces->get(Xpieces, i)->row;
                        int Pcol = Xpieces->get(Xpieces, i)->col;
                        boardArray[Xpieces->get(Xpieces, i)->row][Xpieces->get(Xpieces, i)->col] = '.';
                        Xpieces->get(Xpieces, i)->row = Xpieces->get(Xpieces, i)->row - 1;
                        Xpieces->get(Xpieces, i)->col = Xpieces->get(Xpieces, i)->col + 1;
                        int Nrow = Xpieces->get(Xpieces, i)->row;
                        int Ncol = Xpieces->get(Xpieces, i)->col;
                        boardArray[Xpieces->get(Xpieces, i)->row][Xpieces->get(Xpieces, i)->col] = 'X';
                        updateLog(Prow, Pcol, Nrow, Ncol);
                        puts("MOVED RIGHT RANDOM X");
                        turn = !turn;
                        onGoingTurn = !onGoingTurn;
                    }
                } else if (direction == 1) {
                    int Prow = Xpieces->get(Xpieces, i)->row;
                    int Pcol = Xpieces->get(Xpieces, i)->col;
                    boardArray[Xpieces->get(Xpieces, i)->row][Xpieces->get(Xpieces, i)->col] = '.';
                    Xpieces->get(Xpieces, i)->row = Xpieces->get(Xpieces, i)->row - 1;
                    Xpieces->get(Xpieces, i)->col = Xpieces->get(Xpieces, i)->col + 1;
                    int Nrow = Xpieces->get(Xpieces, i)->row;
                    int Ncol = Xpieces->get(Xpieces, i)->col;
                    boardArray[Xpieces->get(Xpieces, i)->row][Xpieces->get(Xpieces, i)->col] = 'X';
                    updateLog(Prow, Pcol, Nrow, Ncol);
                    puts("MOVED RIGHT X");
                    turn = !turn;
                    onGoingTurn = !onGoingTurn;
                } else if (direction == 2) {
                    int Prow = Xpieces->get(Xpieces, i)->row;
                    int Pcol = Xpieces->get(Xpieces, i)->col;
                    boardArray[Xpieces->get(Xpieces, i)->row][Xpieces->get(Xpieces, i)->col] = '.';
                    Xpieces->get(Xpieces, i)->row = Xpieces->get(Xpieces, i)->row - 1;
                    Xpieces->get(Xpieces, i)->col = Xpieces->get(Xpieces, i)->col - 1;
                    int Nrow = Xpieces->get(Xpieces, i)->row;
                    int Ncol = Xpieces->get(Xpieces, i)->col;
                    boardArray[Xpieces->get(Xpieces, i)->row][Xpieces->get(Xpieces, i)->col] = 'X';
                    updateLog(Prow, Pcol, Nrow, Ncol);
                    puts("MOVED LEFT X");
                    turn = !turn;
                    onGoingTurn = !onGoingTurn;
                }


            }

        } else {
            int i = random() % (Opieces->getSize(Opieces) - 1);
            int d = random() % 2 + 1;
            if (Opieces->get(Opieces, i) == nullptr) {
                while (Opieces->get(Opieces, i) != nullptr) {
                    i = random() % (Opieces->getSize(Opieces) - 1);
                }
            }
            canJump(Opieces->get(Opieces, i));
            if (place[0] != -666 && Xpieces->get(Xpieces, i) != nullptr) {
                int Prow = Opieces->get(Opieces, i)->row;
                int Pcol = Opieces->get(Opieces, i)->col;
                boardArray[Opieces->get(Opieces, i)->row][Opieces->get(Opieces, i)->col] = '.';
                Opieces->get(Opieces, i)->row = place[0];
                Opieces->get(Opieces, i)->col = place[1];
                int Nrow = Opieces->get(Opieces, i)->row;
                int Ncol = Opieces->get(Opieces, i)->col;
                boardArray[place[0]][place[1]] = 'O';
                boardArray[place[2]][place[3]] = '.';
                for (int j = 0; j < Opieces->getSize(Opieces); j++) {
                    if (Xpieces->get(Xpieces, j)->row == place[2] && Xpieces->get(Xpieces, j)->col == place[3]) {
                        Xpieces->removeFromList(Xpieces, Xpieces->get(Xpieces, j));
                    }
                }
                updateLog(Prow, Pcol, Nrow, Ncol);
                puts("JUMPED O ");
                turn = !turn;
                onGoingTurn = !onGoingTurn;
            } else {
                i = random() % Opieces->getSize(Opieces);
                int direction = canMove(Opieces->get(Opieces, i));
                while (direction == 0) {
                    if (tryToMove <= 0) {
                        turn = !turn;
                        onGoingTurn = !onGoingTurn;
                    }
                    i = random() % Opieces->getSize(Opieces);
                    direction = canMove(Opieces->get(Opieces, i));
                    tryToMove--;
                }
                if (direction == 3) {
                    if (d == 1 && Opieces->get(Opieces, i) != nullptr) {
                        int Prow = Opieces->get(Opieces, i)->row;
                        int Pcol = Opieces->get(Opieces, i)->col;
                        boardArray[Opieces->get(Opieces, i)->row][Opieces->get(Opieces, i)->col] = '.';
                        Opieces->get(Opieces, i)->row = Opieces->get(Opieces, i)->row + 1;
                        Opieces->get(Opieces, i)->col = Opieces->get(Opieces, i)->col - 1;
                        int Nrow = Opieces->get(Opieces, i)->row;
                        int Ncol = Opieces->get(Opieces, i)->col;
                        boardArray[Opieces->get(Opieces, i)->row][Opieces->get(Opieces, i)->col] = 'O';
                        updateLog(Prow, Pcol, Nrow, Ncol);
                        puts("MOVED LEFT RANDOM O");
                        turn = !turn;
                        onGoingTurn = !onGoingTurn;

                    } else if (d == 2 && Opieces->get(Opieces, i) != nullptr) {
                        int Prow = Opieces->get(Opieces, i)->row;
                        int Pcol = Opieces->get(Opieces, i)->col;
                        boardArray[Opieces->get(Opieces, i)->row][Opieces->get(Opieces, i)->col] = '.';
                        Opieces->get(Opieces, i)->row = Opieces->get(Opieces, i)->row + 1;
                        Opieces->get(Opieces, i)->col = Opieces->get(Opieces, i)->col + 1;
                        int Nrow = Opieces->get(Opieces, i)->row;
                        int Ncol = Opieces->get(Opieces, i)->col;
                        boardArray[Opieces->get(Opieces, i)->row][Opieces->get(Opieces, i)->col] = 'O';
                        updateLog(Prow, Pcol, Nrow, Ncol);
                        puts("MOVED RIGHT RANDOM O");
                        turn = !turn;
                        onGoingTurn = !onGoingTurn;
                    }
                } else if (direction == 1) {
                    int Prow = Opieces->get(Opieces, i)->row;
                    int Pcol = Opieces->get(Opieces, i)->col;
                    boardArray[Opieces->get(Opieces, i)->row][Opieces->get(Opieces, i)->col] = '.';
                    Opieces->get(Opieces, i)->row = Opieces->get(Opieces, i)->row + 1;
                    Opieces->get(Opieces, i)->col = Opieces->get(Opieces, i)->col + 1;
                    int Nrow = Opieces->get(Opieces, i)->row;
                    int Ncol = Opieces->get(Opieces, i)->col;
                    boardArray[Opieces->get(Opieces, i)->row][Opieces->get(Opieces, i)->col] = 'O';
                    updateLog(Prow, Pcol, Nrow, Ncol);
                    puts("MOVED RIGHT O");
                    turn = !turn;
                    onGoingTurn = !onGoingTurn;
                } else if (direction == 2) {
                    int Prow = Opieces->get(Opieces, i)->row;
                    int Pcol = Opieces->get(Opieces, i)->col;
                    boardArray[Opieces->get(Opieces, i)->row][Opieces->get(Opieces, i)->col] = '.';
                    Opieces->get(Opieces, i)->row = Opieces->get(Opieces, i)->row + 1;
                    Opieces->get(Opieces, i)->col = Opieces->get(Opieces, i)->col - 1;
                    int Nrow = Opieces->get(Opieces, i)->row;
                    int Ncol = Opieces->get(Opieces, i)->col;
                    boardArray[Opieces->get(Opieces, i)->row][Opieces->get(Opieces, i)->col] = 'O';
                    updateLog(Prow, Pcol, Nrow, Ncol);
                    puts("MOVED LEFT O");
                    turn = !turn;
                    onGoingTurn = !onGoingTurn;
                }


            }
        }
    }
}

bool board::canJump(checker *c) {
    int rowMod = 1;
    int colMod = 1;
    if (c->team == 'X') {
        if (boardArray[c->row - rowMod][c->col + colMod] == 'O' && (c->col + 2) <= 7) {
            if (boardArray[c->row - 2][c->col + 2] == '.') {
                place[0] = c->row - 2;
                place[1] = c->col + 2;
                place[2] = c->row - 1;
                place[3] = c->col + 1;
                return true;
            }
        } else if (boardArray[c->row - rowMod][c->col - colMod] == 'O' && (c->col - 2) >= 0) {
            if (boardArray[c->row - 2][c->col - 2] == '.') {
                place[0] = c->row - 2;
                place[1] = c->col - 2;
                place[2] = c->row - 1;
                place[3] = c->col - 1;
                return true;
            }
        } else {
            place[0] = -666;
            place[1] = -666;
            place[2] = -666;
            place[3] = -666;
            return false;
        }
    } else {
        if (c->team == 'O') {
            if (boardArray[c->row + rowMod][c->col + colMod] == 'X' && (c->col + 2) <= 7) {
                if (boardArray[c->row + 2][c->col + 2] == '.') {
                    place[0] = c->row + 2;
                    place[1] = c->col + 2;
                    place[2] = c->row + 1;
                    place[3] = c->col + 1;
                    return true;
                }
            } else if (boardArray[c->row + rowMod][c->col - colMod] == 'X' && (c->col - 2) >= 0) {
                if (boardArray[c->row + 2][c->col - 2] == '.') {
                    place[0] = c->row + 2;
                    place[1] = c->col - 2;
                    place[2] = c->row + 1;
                    place[3] = c->col - 1;
                    return true;
                }
            } else {
                place[0] = -666;
                place[1] = -666;
                place[2] = -666;
                place[3] = -666;
                return false;
            }
        }
    }
    return false;
}

checker *board::canMove(char team) {
    LinkedList *lp = lp->makeEmptyLinkedList();
    bool right = false;
    bool left = false;
    bool kingRight = false;
    bool kingLeft = false;
    if (team == 'X') {
        for (int i = Xpieces->getSize(Xpieces) - 1; i > 0; i--) {
            checker *cc = Xpieces->get(Xpieces, i);
            if (cc->row - 1 == '.' && cc->col + 1 == '.') { // regular right
                right = true;
            }
            if (cc->row - 1 == '.' && cc->col - 1 == '.') { // regular left
                left = true;
            }
            if (cc->king) {
                if (cc->row + 1 == '.' && cc->col + 1 == '.') { // back right
                    kingRight = true;
                }
                if (cc->row + 1 == '.' && cc->col - 1 == '.') { // back left
                    kingLeft = true;
                }
            }
            if (right || left || kingRight || kingLeft) {
                lp->savePayload(lp, cc);
            } else {
                return nullptr;
            }
        }
    } else if (team == 'O') {
        for (int i = Opieces->getSize(Opieces) - 1; i > 0; i--) {
            checker *cc = Opieces->get(Opieces, i);
            if (cc->row - 1 == '.' && cc->col + 1 == '.') { // regular right
                right = true;
            }
            if (cc->row - 1 == '.' && cc->col - 1 == '.') { // regular left
                left = true;
            }
            if (cc->king) {
                if (cc->row + 1 == '.' && cc->col + 1 == '.') { // back right
                    kingRight = true;
                }
                if (cc->row + 1 == '.' && cc->col - 1 == '.') { // back left
                    kingLeft = true;
                }
            }
            if (right || left || kingRight || kingLeft) {
                lp->savePayload(lp, cc);
            } else {
                return nullptr;
            }
        }
    }

    return lp->getRandElt(lp);
}

void board::makeMove(checker* c){
    if(c->team=='X'){
        makeMoveX(c);
    }else{
        makeMoveO(c);
    }
}

void board::makeMoveX(checker* cc){
    bool right = false;
    bool left = false;
    bool kingRight = false;
    bool kingLeft = false;
    bool moves[4] = {right,left,kingRight,kingLeft};
    if (cc->row - 1 == '.' && cc->col + 1 == '.') { // regular right
        right = true;
    }
    if (cc->row - 1 == '.' && cc->col - 1 == '.') { // regular left
        left = true;
    }
    if (cc->king) {
        if (cc->row + 1 == '.' && cc->col + 1 == '.') { // back right
            kingRight = true;
        }
        if (cc->row + 1 == '.' && cc->col - 1 == '.') { // back left
            kingLeft = true;
        }
    }
    int moveIndex = random() % 4;
    switch (moveIndex){
        case 0:
            if(moves[0]){
                cc->setRow(cc->row-1);
                cc->setCol(cc->col+1);
            }
            break;
        case 1:
            if(moves[1]){
                cc->setRow(cc->row-1);
                cc->setCol(cc->col-1);
            }
            break;
        case 2:
            if(moves[2]){
                cc->setRow(cc->row+1);
                cc->setCol(cc->col+1);
            }
            break;
        case 3:
            if(moves[3]){
                cc->setRow(cc->row+1);
                cc->setCol(cc->col-1);
            }
            break;
        default:
            if(right){
                cc->setRow(cc->row-1);
                cc->setCol(cc->col+1);
            }else if(left){
                cc->setRow(cc->row-1);
                cc->setCol(cc->col-1);
            }else if(kingRight){
                cc->setRow(cc->row+1);
                cc->setCol(cc->col+1);
            }else if(kingLeft){
                cc->setRow(cc->row+1);
                cc->setCol(cc->col-1);
            }

    }

}

void board::makeMoveO(checker* cc){
    bool right = false;
    bool left = false;
    bool kingRight = false;
    bool kingLeft = false;
    bool moves[4] = {right,left,kingRight,kingLeft};
    if (cc->row + 1 == '.' && cc->col + 1 == '.') { // regular right
        right = true;
    }
    if (cc->row + 1 == '.' && cc->col - 1 == '.') { // regular left
        left = true;
    }
    if (cc->king) {
        if (cc->row - 1 == '.' && cc->col + 1 == '.') { // back right
            kingRight = true;
        }
        if (cc->row - 1 == '.' && cc->col - 1 == '.') { // back left
            kingLeft = true;
        }
    }
    int moveIndex = random() % 4;
    switch (moveIndex){
        case 0:
            if(moves[0]){
                cc->setRow(cc->row+1);
                cc->setCol(cc->col+1);
            }
            break;
        case 1:
            if(moves[1]){
                cc->setRow(cc->row+1);
                cc->setCol(cc->col-1);
            }
            break;
        case 2:
            if(moves[2]){
                cc->setRow(cc->row-1);
                cc->setCol(cc->col+1);
            }
            break;
        case 3:
            if(moves[3]){
                cc->setRow(cc->row-1);
                cc->setCol(cc->col-1);
            }
            break;
        default:
            if(right){
                cc->setRow(cc->row+1);
                cc->setCol(cc->col+1);
            }else if(left){
                cc->setRow(cc->row+1);
                cc->setCol(cc->col-1);
            }else if(kingRight){
                cc->setRow(cc->row-1);
                cc->setCol(cc->col+1);
            }else if(kingLeft){
                cc->setRow(cc->row-1);
                cc->setCol(cc->col-1);
            }

    }

}

void board::printBoard() {
    for (auto &i : boardArray) {
        for (char j : i) {
            printf(" %c ", j);
        }
        printf("\n");
    }
    printf("\n\n");
}

void board::updateLog(int Prow, int Pcol, int Nrow, int Ncol) {

    fprintf(log, "Moving from [%d , %d] to [%d , %d]\n", Prow, Pcol, Nrow, Ncol);
    fprintf(log, "New Board:\n");
    for (auto &i : boardArray) {
        for (char j : i) {
            fprintf(log, " %c ", j);
        }
        fprintf(log, "\n");
    }
    fprintf(log, "\n\n");


}

board::~board() = default;

void board::checkKing() {
    for (int k = 0; k < Xpieces->getSize(Xpieces); k++) {
        checker *checkr = Xpieces->get(Xpieces, k);
        if (checkr->row == 0 && !checkr->king) {
            checkr->promote();
            puts("X king promoted!!");
        }
    }
    for (int k = 0; k < Opieces->getSize(Opieces); k++) {
        checker *checkr = Opieces->get(Opieces, k);
        if (checkr->row == 7 && !checkr->king) {
            checkr->promote();
            puts("O king promoted!!");
        }
    }
}

void board::resetMoveCount() {
    tryToMove = 10;
    onGoingTurn = true;
}
