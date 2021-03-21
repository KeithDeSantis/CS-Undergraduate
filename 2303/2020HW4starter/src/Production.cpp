/*
 * Production.cpp
 *
 *  Created on: Feb 1, 2020
 *      Author: Therese
 */

#include "Production.h"
#include "LinkedList.h"
#include "AdjMat.h"
#include "Room.h"


Production::Production() {
	// TODO Auto-generated constructor stub

}

Production::~Production() {
	// TODO Auto-generated destructor stub
}

bool Production::prod(int argc, char* argv[]) {
    bool answer = false;
    if (argc <= 1) //no interesting information
    {
        puts("Didn't find any arguments.");
        fflush(stdout);
        answer = false;
    } else {
        printf("Found %d interesting arguments.\n", argc - 1);
        fflush(stdout);
        char filename[FILENAMELENGTHALLOWANCE];
        char *eptr = (char *) malloc(sizeof(char *));
        long aL = -1L;
        int maxRooms = -1;
        float maxTreasure = -1;
        double maxTreas = -1;
        for (int i = 1; i < argc; i++) //don't want to read argv[0]
        {//argv[i] is a string
            //in this program our arguments are NR, NC, gens, filename, print and pause
            //because pause is optional, argc could be 6 or 7
            //because print is optional (if print is not present, neither is pause) argc could be 5
            switch (i) {
                case 1:
                    //this is filename
                    printf("The length of the filename is %d.\n", strlen(argv[i]));
                    printf("The proposed filename is %s.\n", argv[i]);
                    if (strlen(argv[i]) >= FILENAMELENGTHALLOWANCE) {
                        puts("Filename is too long.");
                        fflush(stdout);
                        answer = false;
                    } else {
                        strcpy(filename, argv[i]);
                        printf("Filename was %s.\n", filename);
                        fflush(stdout);
                    }
                    break;
                case 2:
                    //this is maximum number of rooms

                    aL = strtol(argv[i], &eptr, 10);
                    maxRooms = (int) aL;
                    printf("Number of rooms is %d\n", maxRooms);
                    fflush(stdout);
                    break;
                case 3:
                    //this is maximum amount of treasure

                    maxTreas = atof(argv[i]);
                    printf("Amount of  treasure is %f\n", maxTreas);
                    fflush(stdout);
                    maxTreasure = (float) maxTreas;
                    break;

                default:
                    puts("Unexpected argument count.");
                    fflush(stdout);
                    answer = false;
                    break;
            }//end of switch
        }//end of for loop on argument count
        puts("after reading arguments");
        fflush(stdout);
        int nrooms = -1;
        AdjMat *adjMP = new AdjMat;
        Room *theRoomPs[10];
        puts("Before read file");
        fflush(stdout);
        answer = readFile(filename, &nrooms, adjMP, theRoomPs); //read the file
        puts("Back from read file");
        fflush(stdout);
        LinkedList *historyP = new LinkedList();
        historyP = historyP->makeEmptyLinkedList();
        LinkedList *searchQ = new LinkedList();
        searchQ = searchQ->makeEmptyLinkedList();
        puts("starting search");
        fflush(stdout);
        bool done = false;
        int searchedRooms = 0;
        float foundTreasure = 0.0;
        Room* roomBeingSearchedP = theRoomPs[0];
        roomBeingSearchedP->searched = true;
        LinkedList::searchResults *srP = (LinkedList::searchResults *) malloc(sizeof(LinkedList::searchResults));
        srP->roomNumber = 0;
        srP->treasure = roomBeingSearchedP->treasure;
        if ((srP->treasure <= maxTreas) && maxRooms > 0) {
            puts("Enqueuing room 0");
            searchedRooms++;
            foundTreasure += roomBeingSearchedP->treasure;
            historyP->savePayload(historyP, roomBeingSearchedP);
        }
        while (!done) {
            for (int col = 0; (col < nrooms) && !done; col++) {
                printf("checking rooms %d and %d.\n", roomBeingSearchedP->roomNumber, col);
                if (adjMP->getEdge(adjMP, roomBeingSearchedP->roomNumber, col) == 1) {
                    puts("found an edge");
                    if (!(theRoomPs[col]->searched)) {
                        printf("Room %d hasn't already been searched.\n", col);
                        theRoomPs[col]->searched = true;
                        if (theRoomPs[col]->treasure <= maxTreas && (searchedRooms + 1) <= maxRooms) {
                            foundTreasure += theRoomPs[col]->treasure;
                            searchedRooms++;
                            printf("found treasure updated to %f.\n", foundTreasure);
                            printf("enqueuing room %d.\n", col);
                            printf("Before enqueuing queue empty reports %d\n", searchQ->isEmpty(searchQ));
                            searchQ->savePayload(searchQ, theRoomPs[col]);
                            srP = (LinkedList::searchResults *) malloc(sizeof(LinkedList::searchResults));
                            srP->roomNumber = theRoomPs[col]->roomNumber;
                            srP->treasure = theRoomPs[col]->treasure;
                            historyP->savePayload(historyP, theRoomPs[col]);
                            printf("After enqueuing, queue empty reports %d\n", searchQ->isEmpty(searchQ));
                        }
                    }
                }
                if (foundTreasure >= maxTreasure) {
                    done = true;
                    puts("Done by treasure");
                }
                if (searchedRooms >= maxRooms) {
                    done = true;
                    puts("Done by rooms");
                }

            }
            if (searchQ->isEmpty(searchQ)) {
                done = true;
                puts("Done by queue empty");
            }
            if (!done) {
                puts("Invoking dequeue");
                if (searchQ->isEmpty(searchQ)) {
                    puts("Empty");
                }else {
                    puts("Doing the thing");
                    roomBeingSearchedP =  searchQ->dequeueLIFO(searchQ);
                }
            }
        }
        historyP->printHistory(historyP);
    }
    return answer;
}


bool Production::readFile(char* filename, int* nrooms, AdjMat* adjMP, Room** theRoomPs){
        bool ok = false;
        FILE* fp = fopen(filename, "r");
        if(fp==nullptr){
            puts("Error! opening file");
        }
        else{
            fscanf(fp,"%d",nrooms);
            adjMP->n=*nrooms;
            int howManyRooms = *nrooms;
            adjMP->edgesP = (int*) malloc(howManyRooms * howManyRooms *sizeof(int));
            puts("Before init Adj Mat");
            adjMP->init(adjMP);
            int temp = -1;
            for(int roomr = 1; roomr<*nrooms; roomr++){
                printf("on row %d\n", roomr);
                for(int roomc = 0; roomc<roomr; roomc++){
                    fscanf(fp,"%d", &temp);
                    printf("in column %d, read %d\n", roomc, temp);
                    if(temp==1){
                        adjMP->setEdge(adjMP,roomr,roomc);
                    }
                }
            }
            float tempTreas = 2.9;
            for(int roomr = 0; roomr<*nrooms; roomr++){
                fscanf(fp,"%f", &tempTreas);
                Room** aRoomP = theRoomPs;
                aRoomP +=roomr;
                *aRoomP = (Room*) malloc(sizeof(Room));
                (*aRoomP)->treasure = tempTreas;
                (*aRoomP)->roomNumber = roomr;
                printf("The treasure in room %d is %.1f\n", roomr, (*aRoomP)->treasure);
            }
            ok = true;
        }
        fclose(fp);
        return ok;
    }


