//
// Created by 14132 on 2/1/2020.
//
#include "brain.h"



void mastermind(int* theSpaceP, int max) {
    LLNode *node = makeEmptyLinkedList();
    int col = rand() % 20;
    int row = rand() % 20;
    Marker* markSTART = set(theSpaceP, col, row, 1);
    printf("%d\n", markSTART->index);
    printSpace(theSpaceP, 20, 20);
    savePayload(node, markSTART);
    for (int i = 2; i<max; i++){
        int moveDir =  rand() % 4;
        switch(moveDir){
            case 0:
                if(row == 0){
                    row = 19;
                    Marker* mark = set(theSpaceP, col, row, i);
                    savePayload(node, mark);
                    printf("%d\n", mark->index);
                    printSpace(theSpaceP, 20, 20);
                }else{
                    row--;
                    Marker* mark = set(theSpaceP, col, row,i);
                    savePayload(node, mark);
                    printf("%d\n", mark->index);
                    printSpace(theSpaceP, 20, 20);
                }break;
            case 1:
                if(row == 19){
                    col = 0;
                    Marker* mark = set(theSpaceP, col, row,i);
                    savePayload(node, mark);
                    printf("%d\n", mark->index);
                    printSpace(theSpaceP, 20, 20);
                }else{
                    col++;
                    Marker* mark = set(theSpaceP, col, row,i);
                    savePayload(node, mark);
                    printf("%d\n", mark->index);
                    printSpace(theSpaceP, 20, 20);
                }break;
            case 2:
                if(row == 19){
                    row = 0;
                    Marker* mark = set(theSpaceP, col, row,i);
                    savePayload(node, mark);
                    printf("%d\n", mark->index);
                    printSpace(theSpaceP, 20, 20);
                }else{
                    row++;
                    Marker* mark = set(theSpaceP, col, row,i);
                    savePayload(node, mark);
                    printf("%d\n", mark->index);
                    printSpace(theSpaceP, 20, 20);
                }break;
                case 3:
                    if(col == 0){
                        col = 19;
                        Marker* mark = set(theSpaceP, col, row,i);
                        savePayload(node, mark);
                        printf("%d\n", mark->index);
                        printSpace(theSpaceP, 20, 20);
                    }else{
                        col--;
                        Marker* mark = set(theSpaceP, col, row,i);
                        savePayload(node, mark);
                        printf("%d\n", mark->index);
                        printSpace(theSpaceP, 20, 20);
                    }break;
            default: printf("RNG ERROR\n");
            break;
        }

}

    LLNode* temp = &node;
   while(temp->next){
       printf("index:%d at row: %d col:%d\n", temp->payP->index,temp->payP->row,temp->payP->col);
       temp=(LLNode*)temp->next;
   }
    printf("index:%d at row: %d col:%d\n", temp->payP->index,temp->payP->row,temp->payP->col);

}