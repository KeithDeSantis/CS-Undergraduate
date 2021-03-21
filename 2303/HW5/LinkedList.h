//
// Created by 14132 on 3/1/2020.//

#ifndef SRC_LINKEDLIST_H
#define SRC_LINKEDLIST_H

#include "checker.h"

class LinkedList {
public:
    LinkedList();
    virtual ~LinkedList();


    typedef  checker Payload;


    struct LLNode* next;
    struct LLNode* prev;
    Payload* payP;

    typedef struct{
        Payload* mp;
        LLNode* newQHead;
    }   backFromDQFIFO;

    LinkedList * makeEmptyLinkedList();

    bool isEmpty(LinkedList *lp);

    void savePayload(LinkedList *lp, Payload *mp);

    LinkedList* removeFromList(LinkedList* hp, Payload* p);

    backFromDQFIFO* dequeueFIFO(LinkedList* lp);

    void printToFile(FILE*, int , float );

    Payload* get(LinkedList*, int );

    int getSize(LinkedList*);

    Payload* getRandElt(LinkedList*);

};


#endif //SRC_LINKEDLIST_H
