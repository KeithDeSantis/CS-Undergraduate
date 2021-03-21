//
// Created by Kush Shah on 2/18/20.
//

#ifndef SRC_ADJMAT_H
#define SRC_ADJMAT_H


class AdjMat {
public:
    int n = -1;
    int* edgesP = nullptr;
    AdjMat();
    virtual ~AdjMat();

    void init(AdjMat *adjMP);

    void setEdge(AdjMat *adjMP, int row, int col);

    int getEdge(AdjMat *adjMP, int row, int col);
};


#endif //SRC_ADJMAT_H
