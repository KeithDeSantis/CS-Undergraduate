//
// Created by Kush Shah on 2/18/20.
//

#ifndef SRC_LINKEDLIST_H
#define SRC_LINKEDLIST_H

#include "Room.h"

class LinkedList {
public:
        LinkedList();
        virtual ~LinkedList();
        typedef struct {
            int roomNumber;
            float treasure;
        }searchResults;

        typedef Room Payload;
        typedef searchResults Payload2;

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

        Payload *dequeueLIFO(LinkedList *lp);

        void printHistory(LinkedList *hp);

        LinkedList* removeFromList(LinkedList* hp, Payload* p);

        backFromDQFIFO* dequeueFIFO(LinkedList* lp);

        void printToFile(FILE*, int , float );

};


#endif //SRC_LINKEDLIST_H
