//
// Created by kushshah on 2/16/21.
//

#include "fedoops.h"

int main() {
    char c[1000];
    FILE *seedFile = fopen("seed.txt", "red");
    fscanf(seedFile, "%[^\n]", c);
    int seed = atoi(c);
    srand(seed);

    for (int i = 0; i < PACKAGES; ++i) {
        initPackage(&ppp[i], i);
    }
    EMPTY.ID = -1;
    for (int i = 0; i < 4; ++i) {
        station[i] = EMPTY;
    }
    pthread_t green1, green2, green3, green4, green5, green6, green7, green8, green9, green10,
            red1, red2, red3, red4, red5, red6, red7, red8, red9, red10,
            yellow1, yellow2, yellow3, yellow4, yellow5, yellow6, yellow7, yellow8, yellow9, yellow10,
            blue1, blue2, blue3, blue4, blue5, blue6, blue7, blue8, blue9, blue10;
    pthread_t green[10] = {green1, green2, green3, green4, green5, green6, green7, green8, green9, green10};
    pthread_t red[10] = {red1, red2, red3, red4, red5, red6, red7, red8, red9, red10};
    pthread_t yellow[10] = {yellow1, yellow2, yellow3, yellow4, yellow5, yellow6, yellow7, yellow8, yellow9, yellow10};
    pthread_t blue[10] = {blue1, blue2, blue3, blue4, blue5, blue6, blue7, blue8, blue9, blue10};

    sem_init(&greenSem, 0, 1);
    sem_init(&redSem, 0, 1);
    sem_init(&yellowSem, 0, 1);
    sem_init(&blueSem, 0, 1);

    sem_init(&stationSem, 0, 1);

    sem_init(&scale, 0, 1);
    sem_init(&barcode, 0, 1);
    sem_init(&shaker, 0, 1);
    sem_init(&xray, 0, 1);
    sem_init(&pppSem, 0, 1);

    sem_init(&choosing, 0, 1);
    //creating
    for (int i = 0; i < 10; ++i) { //TODO: 10
        pthread_create(&green[i], 0, greenTeam, i);
    }
    for (int i = 0; i < 10; ++i) {
        pthread_create(&red[i], 0, redTeam, i);
    }
    for (int i = 0; i < 10; ++i) {
        pthread_create(&yellow[i], 0, yellowTeam, i);
    }
    for (int i = 0; i < 10; ++i) {
        pthread_create(&blue[i], 0, blueTeam, i);
    }

    //joining
    for (int i = 0; i < 10; ++i) { //TODO: 10
        pthread_join(green[i], 0);
    }
    for (int i = 0; i < 10; ++i) {
        pthread_join(red[i], 0);
    }
    for (int i = 0; i < 10; ++i) {
        pthread_join(yellow[i], 0);
    }
    for (int i = 0; i < 10; ++i) {
        pthread_join(blue[i], 0);
    }

    sem_destroy(&greenSem);
    sem_destroy(&yellowSem);
    sem_destroy(&redSem);
    sem_destroy(&blueSem);

    sem_destroy(&stationSem);

    sem_destroy(&scale);
    sem_destroy(&barcode);
    sem_destroy(&shaker);
    sem_destroy(&xray);
    sem_destroy(&pppSem);
    sem_destroy(&choosing);


    printf("All %d packages have been put on the truck\n",PACKAGES);
}


void greenTeam(int me) {
    sem_wait(&greenSem);
    while (howManyLeft()) {
        int times[4];
        for (int i = 0; i < 4; ++i) {
            times[i] = rand() % 3 + 1;
        }

        sem_wait(&pppSem);
        bool hasPackage = false;
        struct package *myPack;
        while (!hasPackage) {
            for (int i = 0; i < PACKAGES; ++i) {
                if (ppp[i].status == UNTOUCHED) {
                    ppp[i].status = TAKEN;
                    myPack = &ppp[i];
                    hasPackage = !hasPackage;
                    break;
                }
            }
        }
        sem_post(&pppSem);
        printf("[Team Green Worker%d]: I have package #%d\n", me, myPack->ID);
        printf("[Team Green Worker%d]: The instructions for my package are:\n", me);
        printInstructions(myPack->instructions);
        //grab sems
        sem_wait(&choosing);
        for (int i = 0; i < 4; ++i) {
            switch (myPack->instructions[i]) {
                case 1:
                    sem_wait(&scale);
                    break;
                case 2:
                    sem_wait(&barcode);
                    break;
                case 3:
                    sem_wait(&shaker);
                    break;
                case 4:
                    sem_wait(&xray);
                    break;
                default:
                    break;
            }
        }
        sem_post(&choosing);

        sem_wait(&stationSem);
        for (int i = 0; i < 4; ++i) {
            if (myPack->instructions[i] == 0) {//all done
                sem_wait(&pppSem);
                ppp[myPack->ID].status = 5;
                sem_post(&pppSem);
                break;
            }
            printf("[Team Green Worker%d]: I am getting on station #%d\n", me, myPack->instructions[i]);
            myPack->status = myPack->instructions[i];
            station[myPack->instructions[i] - 1] = *myPack;
            sem_post(&stationSem);
            sleep(times[i]);
            printStations();
            sem_wait(&stationSem);
            station[myPack->instructions[i] - 1] = EMPTY;
            printf("[Team Green Worker%d]: I have gotten off station #%d\n", me, myPack->instructions[i]);
            switch (myPack->instructions[i]) {
                case 1:
                    sem_post(&scale);
                    break;
                case 2:
                    sem_post(&barcode);
                    break;
                case 3:
                    sem_post(&shaker);
                    break;
                case 4:
                    sem_post(&xray);
                    break;
                default:
                    break;
            }

        }
        sem_post(&stationSem);
        printStations();
        sem_post(&greenSem);
        sleep(1);
        sem_wait(&greenSem);
    }
    for (int i = 0; i < 10; ++i) {
        sem_post(&greenSem);
    }
    pthread_exit(0);
}

void redTeam(int me) {
    sem_wait(&redSem);
    while (howManyLeft()) {
        int times[4];
        for (int i = 0; i < 4; ++i) {
            times[i] = rand() % 3 + 1;
        }

        sem_wait(&pppSem);
        bool hasPackage = false;
        struct package *myPack;
        while (!hasPackage) {
            for (int i = 0; i < PACKAGES; ++i) {
                if (ppp[i].status == UNTOUCHED) {
                    ppp[i].status = TAKEN;
                    myPack = &ppp[i];
                    hasPackage = !hasPackage;
                    break;
                }
            }
        }
        sem_post(&pppSem);
        printf("[Team Red Worker%d]: I have package #%d\n", me, myPack->ID);
        printf("[Team Red Worker%d]: The instructions for my package are:\n", me);
        printInstructions(myPack->instructions);
        //grab sems
        sem_wait(&choosing);
        for (int i = 0; i < 4; ++i) {
            switch (myPack->instructions[i]) {
                case 1:
                    sem_wait(&scale);
                    break;
                case 2:
                    sem_wait(&barcode);
                    break;
                case 3:
                    sem_wait(&shaker);
                    break;
                case 4:
                    sem_wait(&xray);
                    break;
                default:
                    break;
            }
        }
        sem_post(&choosing);

        sem_wait(&stationSem);
        for (int i = 0; i < 4; ++i) {
            if (myPack->instructions[i] == 0) {//all done
                sem_wait(&pppSem);
                ppp[myPack->ID].status = 5;
                sem_post(&pppSem);
                break;
            }
            printf("[Team Red Worker%d]: I am getting on station #%d\n", me, myPack->instructions[i]);
            myPack->status = myPack->instructions[i];
            station[myPack->instructions[i] - 1] = *myPack;
            sem_post(&stationSem);
            sleep(times[i]);
            printStations();
            sem_wait(&stationSem);
            station[myPack->instructions[i] - 1] = EMPTY;
            printf("[Team Red Worker%d]: I have gotten off station #%d\n", me, myPack->instructions[i]);
            switch (myPack->instructions[i]) {
                case 1:
                    sem_post(&scale);
                    break;
                case 2:
                    sem_post(&barcode);
                    break;
                case 3:
                    sem_post(&shaker);
                    break;
                case 4:
                    sem_post(&xray);
                    break;
                default:
                    break;
            }

        }
        sem_post(&stationSem);
        printStations();
        sem_post(&redSem);
        sleep(1);
        sem_wait(&redSem);
    }
    for (int i = 0; i < 10; ++i) {
        sem_post(&redSem);
    }
    pthread_exit(0);
}

void yellowTeam(int me) {
    sem_wait(&yellowSem);
    while (howManyLeft()) {
        int times[4];
        for (int i = 0; i < 4; ++i) {
            times[i] = rand() % 3 + 1;
        }

        sem_wait(&pppSem);
        bool hasPackage = false;
        struct package *myPack;
        while (!hasPackage) {
            for (int i = 0; i < PACKAGES; ++i) {
                if (ppp[i].status == UNTOUCHED) {
                    ppp[i].status = TAKEN;
                    myPack = &ppp[i];
                    hasPackage = !hasPackage;
                    break;
                }
            }
        }
        sem_post(&pppSem);
        printf("[Team Yellow Worker%d]: I have package #%d\n", me, myPack->ID);
        printf("[Team Yellow Worker%d]: The instructions for my package are:\n", me);
        printInstructions(myPack->instructions);
        //grab sems
        sem_wait(&choosing);
        for (int i = 0; i < 4; ++i) {
            switch (myPack->instructions[i]) {
                case 1:
                    sem_wait(&scale);
                    break;
                case 2:
                    sem_wait(&barcode);
                    break;
                case 3:
                    sem_wait(&shaker);
                    break;
                case 4:
                    sem_wait(&xray);
                    break;
                default:
                    break;
            }
        }
        sem_post(&choosing);


        sem_wait(&stationSem);
        for (int i = 0; i < 4; ++i) {
            if (myPack->instructions[i] == 0) {//all done
                sem_wait(&pppSem);
                ppp[myPack->ID].status = 5;
                sem_post(&pppSem);
                break;
            }
            printf("[Team Yellow Worker%d]: I am getting on station #%d\n", me, myPack->instructions[i]);
            myPack->status = myPack->instructions[i];
            station[myPack->instructions[i] - 1] = *myPack;
            sem_post(&stationSem);
            sleep(times[i]);
            printStations();
            sem_wait(&stationSem);
            station[myPack->instructions[i] - 1] = EMPTY;
            printf("[Team Yellow Worker%d]: I have gotten off station #%d\n", me, myPack->instructions[i]);
            switch (myPack->instructions[i]) {
                case 1:
                    sem_post(&scale);
                    break;
                case 2:
                    sem_post(&barcode);
                    break;
                case 3:
                    sem_post(&shaker);
                    break;
                case 4:
                    sem_post(&xray);
                    break;
                default:
                    break;
            }

        }
        sem_post(&stationSem);
        printStations();
        sem_post(&yellowSem);
        sleep(1);
        sem_wait(&yellowSem);
    }
    for (int i = 0; i < 10; ++i) {
        sem_post(&yellowSem);
    }
    pthread_exit(0);
}

void blueTeam(int me) {
    sem_wait(&blueSem);
    while (howManyLeft()) {
        int times[4];
        for (int i = 0; i < 4; ++i) {
            times[i] = rand() % 3 + 1;
        }

        sem_wait(&pppSem);
        bool hasPackage = false;
        struct package *myPack;
        while (!hasPackage) {
            for (int i = 0; i < PACKAGES; ++i) {
                if (ppp[i].status == UNTOUCHED) {
                    ppp[i].status = TAKEN;
                    myPack = &ppp[i];
                    hasPackage = !hasPackage;
                    break;
                }
            }
        }
        sem_post(&pppSem);
        printf("[Team Blue Worker%d]: I have package #%d\n", me, myPack->ID);
        printf("[Team Blue Worker%d]: The instructions for my package are:\n", me);
        printInstructions(myPack->instructions);
        //grab sems
        sem_wait(&choosing);
        for (int i = 0; i < 4; ++i) {
            switch (myPack->instructions[i]) {
                case 1:
                    sem_wait(&scale);
                    break;
                case 2:
                    sem_wait(&barcode);
                    break;
                case 3:
                    sem_wait(&shaker);
                    break;
                case 4:
                    sem_wait(&xray);
                    break;
                default:
                    break;
            }
        }
        sem_post(&choosing);

        sem_wait(&stationSem);
        for (int i = 0; i < 4; ++i) {
            if (myPack->instructions[i] == 0) {//all done
                sem_wait(&pppSem);
                ppp[myPack->ID].status = 5;
                sem_post(&pppSem);
                break;
            }
            printf("[Team Blue Worker%d]: I am getting on station #%d\n", me, myPack->instructions[i]);
            myPack->status = myPack->instructions[i];
            station[myPack->instructions[i] - 1] = *myPack;
            sem_post(&stationSem);
            sleep(times[i]);
            printStations();
            sem_wait(&stationSem);
            station[myPack->instructions[i] - 1] = EMPTY;
            printf("[Team Blue Worker%d]: I have gotten off station #%d\n", me, myPack->instructions[i]);
            switch (myPack->instructions[i]) {
                case 1:
                    sem_post(&scale);
                    break;
                case 2:
                    sem_post(&barcode);
                    break;
                case 3:
                    sem_post(&shaker);
                    break;
                case 4:
                    sem_post(&xray);
                    break;
                default:
                    break;
            }

        }
        sem_post(&stationSem);
        printStations();
        sem_post(&blueSem);
        sleep(1);
        sem_wait(&blueSem);
    }
    for (int i = 0; i < 10; ++i) {
        sem_post(&blueSem);
    }
    pthread_exit(0);
}

void initPackage(struct package *p, int id) {
    int num = rand() % 4;
    p->ID = id;
    p->status = UNTOUCHED;
    p->instructions[0] = rand() % 4 + 1;
    for (int i = 1; i < num; ++i) {
        int instruction = rand() % 4 + 1;
        while (inArray(p->instructions, instruction)) {
            instruction = rand() % 4 + 1;
        }
        p->instructions[i] = instruction;
    }
    printf("Initialized package #%d\n", id);
}

bool inArray(int a[], int b) {
    int len = (sizeof(a)) / (sizeof(a[0]));
    for (int i = 0; i < len; ++i) {
        if (a[i] == b) {
            return true;
        }
    }
    return false;
}

void printInstructions(int *instructions) {
    printf("\t\t\t{ ");
    for (int i = 0; i < 4; ++i) {
        printf("%d, ", instructions[i]);
    }
    printf("}\n");
}

int howManyLeft() {
    int count = 0;
    sem_wait(&pppSem);
    for (int i = 0; i < PACKAGES; ++i) {
        if (ppp[i].status < 0) {
            count++;
        }
    }
    sem_post(&pppSem);
    return count;
}

void printStations() {
    sem_wait(&stationSem);
    printf("\t\t\tStation: { ");
    for (int i = 0; i < 4; ++i) {
        printf("%d, ", station[i].ID);
    }
    printf("}\n");
    sem_post(&stationSem);
}
