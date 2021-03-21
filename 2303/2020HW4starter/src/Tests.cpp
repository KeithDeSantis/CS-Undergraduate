/*
 * Tests.cpp
 *
 *  Created on: Feb 1, 2020
 *      Author: Therese
 */

#include "Tests.h"
#include <stdbool.h>

Tests::Tests() {
	// TODO Auto-generated constructor stub

}

Tests::~Tests() {
	// TODO Auto-generated destructor stub
}

bool Tests::tests()
{
    bool ok1 = testReadFile();
    bool ok2 = testEnqueue();
    bool ok3 = testMakeLList();
    bool ok4 = testRemoveFromList();
    bool ok5 = testPrintHistory();
    bool ok6 = testWriteFile();
	bool answer = ok1 && ok2 && ok3 && ok4 && ok5 && ok6;
	return answer;
}
bool Tests::testReadFile()
{
    Production* p = new Production();
    puts("starting testReadFile");
    bool ok = false;
    //the file tells how many rooms there are
    int answer = -1;
    int rightAnswer = 8;


    AdjMat* adjMP = (AdjMat*) malloc(sizeof(AdjMat));
    Room* theRoomPs[10];//addresses for 10 rooms


    ok = p->readFile("houseGraph.txt", &answer, adjMP, theRoomPs); //read the file
    if(ok)
    {
        if(answer!=rightAnswer)
        {
            puts("test failed on number of rooms");
        }

    }
    puts("The adjacency matrix");
    for(int i = 0; i<answer; i++)
    {
        for(int j = 0; j<answer; j++)
        {
            printf("%d",adjMP->getEdge(adjMP, i, j));

        }
        printf("\n");
    }
    puts("The treasure values");
    for(int i = 0; i<answer; i++)
    {
        printf("%.1f\n", theRoomPs[i]->treasure);
    }
    delete(p);
    return ok;
}

bool Tests::testEnqueue(){
    bool ok = true;
    LinkedList* theListP = theListP->makeEmptyLinkedList();
    int x = 150;
    theListP->savePayload(theListP, (LinkedList::Payload*) x);
    if((int*) theListP->dequeueFIFO(theListP)->mp != (int*) x){
        ok = false;
    }

    return ok;
}

bool Tests::testMakeLList()
{
    bool ok = true;
    puts("starting testMakeLList");fflush(stdout);
    //what are the criteria for success for make LList?
    //should be able to make one, add data to it, get the data back
    //test case 1:
    LinkedList* theListP = theListP->makeEmptyLinkedList();
    bool rightAnswer = true;
    bool answer = theListP->isEmpty(theListP);
    if(answer!=rightAnswer)
    {
        ok = false;
    }
    //test case 2:
    int x = 150;
    theListP->savePayload(theListP, (LinkedList::Payload*) x);
    if((int*) theListP->dequeueFIFO(theListP)->mp != (int*) x){
        ok = false;
    }
    if(!ok)
    {
        puts("test make LList did not pass.");
    }

    return ok;
}

bool Tests::testRemoveFromList()
{
    bool ok = true;
    //cases:
    //1 list is empty:return same list
    //2 list is of length one, and item is present: return empty list
    //3 list is of length one, and item is not present: return same list
    //4 list is longer than one, item is present, at first location: return list starting at second element
    //5 list is longer than one, item is present, not at first location: return list with item removed
    //6 list is longer than one, item is not present: return same list
    LinkedList* case1 = case1->makeEmptyLinkedList();
    LinkedList::Payload* pay1 = (LinkedList::Payload*) malloc(sizeof(Room));
    pay1->roomNumber = 1;
    LinkedList* ans = case1->removeFromList(case1, pay1);
    if((ans != case1) || (ans->payP != (LinkedList::Payload*)0))
    {
        ok = false;

    }
    printf("testRemove case 1 with %d\n", ok); fflush(stdout);
    case1->savePayload(case1, pay1);
    //this is case2
    ans = case1->removeFromList(case1, pay1);
    if((ans != case1) || (ans->payP != (LinkedList::Payload*)0))
    {
        ok = false;

    }
    printf("testRemove case 2 with %d\n", ok); fflush(stdout);
    //now case 3
    LinkedList::Payload* pay3 = (LinkedList::Payload*) malloc(sizeof(Room));
    pay3->roomNumber = 3;
    ans = case1->removeFromList(case1, pay3);
    if(ans != case1)//this is only a partial check for list unchanged
    {
        ok = false;

    }
    printf("testRemove case 3 with %d\n", ok); fflush(stdout);
    //now case 4
    case1 = case1->makeEmptyLinkedList();
    pay1 = (LinkedList::Payload*) malloc(sizeof(Room));
    pay1->roomNumber = 1;
    case1->savePayload(case1, pay1);
    pay3 = (LinkedList::Payload*) malloc(sizeof(Room));
    pay3->roomNumber = 3;
    case1->savePayload(case1, pay3);
    ans = case1->removeFromList(case1, pay1);

    if(ans == case1)
    {
        ok = false;

    }
    printf("testRemove case 4 with %d\n", ok); fflush(stdout);
    //now case 5
    case1 = case1->makeEmptyLinkedList();
    pay1 = (LinkedList::Payload*) malloc(sizeof(Room));
    pay1->roomNumber = 1;
    case1->savePayload(case1, pay1);
    pay3 = (LinkedList::Payload*) malloc(sizeof(Room));
    pay3->roomNumber = 3;
    case1->savePayload(case1, pay3);
    //puts("trying case 5");fflush(stdout);
    ans = case1->removeFromList(case1, pay3);//ans should be equal to case1
    LinkedList* theNext = (LinkedList*) ans->next; //this is element where pay3 got attached
    LinkedList::Payload* check = nullptr;
    if (theNext)
    {
        check = theNext->payP; //this should be pay3, which should have been removed
    }
    //printf("testRemove returned from case 5\n"); fflush(stdout);
    if((ans != case1) || (check != nullptr))//disquiet
    {
        ok = false;

    }
    //printf("ans == case1 is %d\n", ans==case1);
    //printf("check != 0 is %d\n", check != (Payload*)0);
    printf("testRemove case 5 with %d\n", ok); fflush(stdout);
    //now case 6
    case1 = case1->makeEmptyLinkedList();
    pay1 = (LinkedList::Payload*) malloc(sizeof(Room));
    pay1->roomNumber = 1;
    case1->savePayload(case1, pay1);
    pay3 = (LinkedList::Payload*) malloc(sizeof(Room));
    pay3->roomNumber = 3;
    case1->savePayload(case1, pay3);
    LinkedList::Payload* another = (LinkedList::Payload*) malloc(sizeof(Room));
    another->roomNumber=2;
    ans = case1->removeFromList(case1, another);
    if((ans != case1))
    {
        ok = false;

    }
    printf("testRemove case 6 with %d\n", ok); fflush(stdout);
    return ok;
}

bool Tests::testPrintHistory()
{
    bool ok = true;
    return ok;
}

bool Tests::testWriteFile(){
    bool ok = true;
    FILE* test = fopen("test.txt","w+");
    LinkedList* ll = new LinkedList();
    ll->printToFile(test,100,30.30);
    fclose(test);
    return ok;
}