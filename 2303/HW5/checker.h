//
// Created by 14132 on 3/1/2020.//

#ifndef HW5_CHECKER_H
#define HW5_CHECKER_H



class checker {
public:
    checker(int row, int col, char team);
    void promote();

    virtual ~checker();

    void setRow(int row);

    void setCol(int col);

    int row;
    int col;
    char team;
    bool king;

};


#endif //HW5_CHECKER_H
