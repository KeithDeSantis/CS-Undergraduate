//
// Created by 14132 on 3/1/2020.//

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

LinkedList::Payload* LinkedList::get(LinkedList* lp, int index){
    int count = 0;
    LinkedList* temp = lp;
    while(temp->next&&count!=index){
        count++;
        temp=(LinkedList*)temp->next;
    }
    return temp->payP;

}

LinkedList::Payload* LinkedList::getRandElt(LinkedList* lp){
    int index = random() % (lp->getSize(lp) - 1);
    return lp->get(lp,index);
}

int LinkedList::getSize(LinkedList* lp) {
    int count = 0;
    LinkedList* temp = lp;
    while(temp->next){
        count++;
        temp = (LinkedList*)temp->next;
    }
    count++;
    return count;
}

