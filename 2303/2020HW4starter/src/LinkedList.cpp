//
// Created by Kush Shah on 2/18/20.
//
#include <iostream>
using namespace std;
#include "LinkedList.h"

LinkedList::LinkedList(){

}

LinkedList::~LinkedList(){

}

bool LinkedList::isEmpty(LinkedList* lp){
    bool ans = false;
    if(lp->payP==nullptr){
        ans = true;
    }
    return ans;
}

LinkedList* LinkedList::makeEmptyLinkedList(){
    LinkedList* lp = new LinkedList();
    lp->next= nullptr;
    lp->prev= nullptr;
    lp->payP= nullptr;
    return lp;
}

void LinkedList::savePayload(LinkedList* lp, Payload* mp){
    if(isEmpty(lp)){
        lp->payP=mp;
    }else{
        LinkedList* temp = lp;
        while(temp->next){
            temp=(LinkedList*) temp->next;
        }
        LinkedList* newList = makeEmptyLinkedList();
        newList->payP = mp;
        temp->next = (LLNode*) newList;
        newList->prev = (LLNode*) temp;
    }
}

LinkedList::backFromDQFIFO* LinkedList::dequeueFIFO(LinkedList* lp){
    backFromDQFIFO* fp = new backFromDQFIFO;
    if(lp->next == nullptr){
        fp->newQHead = (LLNode*) lp;
    }else{
        fp->newQHead= (LLNode*) lp->next;
    }
    fp->mp= lp->payP;//all return values are set
    if(lp->next != nullptr)
    {
        //length list is >1
        free(lp);
    }

    return fp;
}

LinkedList::Payload* LinkedList::dequeueLIFO(LinkedList* lp){
    Payload* payload = nullptr;
    if(isEmpty(lp)){
        cout << "Trying to dequeue from empty." << endl;
    }else{
        LinkedList* temp = lp;
        while(temp->next){
            temp=(LinkedList*)temp->next;
        }

        payload = temp->payP;
        temp->payP= nullptr;
        printf("Room being returned is %d\n", payload->roomNumber);
        if(temp->prev){
            temp=(LinkedList*)temp->prev;
            printf("end of queue is room %d\n", temp->payP->roomNumber);
            temp->next = nullptr;
        }else{
            cout << "Queue is now empty" << endl;
        }
        cout << "returning from dequeue" << endl;
    }
    return payload;
}

void LinkedList::printHistory(LinkedList* hp){
    FILE* output = fopen("output.txt","w+");

    cout << "Printing History" << endl;
    if(hp->payP == nullptr){
        cout << "Empty" << endl;
    }else{
        float treasureSubtotal = 0.0;
        int room = -1;
        LinkedList* temp = hp;
        while(temp->next){
            room = temp->payP->roomNumber;
            treasureSubtotal+= temp->payP->treasure;
            printf("The room was %d, and the treasure subtotal was %f.\n", room, treasureSubtotal);
            printToFile(output,room,treasureSubtotal);
            temp = (LinkedList*) temp->next;
        }
        room = temp->payP->roomNumber;
        treasureSubtotal+= temp->payP->treasure;
        printf("The room was %d, and the treasure subtotal was %f.\n", room, treasureSubtotal);
        printToFile(output,room,treasureSubtotal);

    }
    fclose(output);
}

void LinkedList::printToFile(FILE* output, int room, float subTotal) {
    fprintf(output,"%d %f.\n",room,subTotal);
}

LinkedList* LinkedList::removeFromList(LinkedList* hp, Payload* p){
    LinkedList* retHead = hp;
    if(isEmpty(hp)){

    }else{
        LinkedList* altHead = (LinkedList*)hp->next;
        LinkedList* temp = hp;
        bool done = false;
        while((!done) && temp->next){
            if(temp->payP == p){
                done = true;
            }else{
                temp = (LinkedList*) temp->next;
            }
        }
        if((temp->payP) ==p){
            if(temp == hp){
                if(!(temp->next)){
                    hp->payP = nullptr;
                }else{
                    retHead = altHead;
                }
            }else{
                LinkedList* prevPart = (LinkedList*) temp->prev;
                LinkedList* nextPart = (LinkedList*) temp->next;
                prevPart->next = (LLNode*) nextPart;
                if((bool) nextPart){
                    nextPart->prev = (LLNode*) prevPart;
                }
            }
        }else{

        }

    }
    return retHead;
}

