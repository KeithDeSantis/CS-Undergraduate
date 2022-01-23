//
// Created by kushshah on 2/15/21.
//
#include "stage.h"

pthread_mutex_t the_mutex;
pthread_mutex_t how_mutex;
pthread_cond_t condD, condJ, condS;
int stage[4] = {EMPTY, EMPTY, EMPTY, EMPTY};
int dancerHistory;
int jugglerHistory;
int performerCounter;

int main1() {
    char c[1000];
    FILE* seedFile = fopen("seed.txt","red");
    fscanf(seedFile,"%[^\n]",c);
    int seed = atoi(c);
    srand(seed);
    pthread_mutex_init(&the_mutex, 0);
    pthread_mutex_init(&how_mutex, 0);
    pthread_t dancer1, dancer2, dancer3, dancer4, dancer5, dancer6, dancer7,
            dancer8, dancer9, dancer10, dancer11, dancer12, dancer13, dancer14, dancer15;
    pthread_t dancers[15] = {dancer1, dancer2, dancer3, dancer4, dancer5, dancer6, dancer7,
                             dancer8, dancer9, dancer10, dancer11, dancer12, dancer13, dancer14, dancer15};
    pthread_t juggler1, juggler2, juggler3, juggler4, juggler5, juggler6, juggler7, juggler8;
    pthread_t jugglers[8] = {juggler1, juggler2, juggler3, juggler4, juggler5, juggler6, juggler7, juggler8};
    pthread_t soloist1, soloist2;
    pthread_t soloists[2] = {soloist1, soloist2};
    pthread_cond_init(&condD, 0);
    pthread_cond_init(&condJ, 0);
    pthread_cond_init(&condS, 0);

    for (int i = 0; i < 15; ++i) {
        pthread_create(&dancers[i], 0, dancer, i+1);
    }
    for (int i = 0; i < 8; ++i) {
        pthread_create(&jugglers[i], 0, juggler, i+1);
    }
    for (int i = 0; i < 2; ++i) {
        pthread_create(&soloists[i], 0, soloist, i+1);
    }
    for (int i = 0; i < 15; ++i) {
        pthread_join(dancers[i], 0);
    }

    for (int i = 0; i < 8; ++i) {
        pthread_join(jugglers[i], 0);
    }

    for (int i = 0; i < 2; ++i) {
        pthread_join(soloists[i], 0);
    }

    printf("Performance over\n");
    pthread_cond_destroy(&condD);
    pthread_cond_destroy(&condJ);
    pthread_cond_destroy(&condS);
    pthread_mutex_destroy(&the_mutex);
    pthread_mutex_destroy(&how_mutex);
    printStage(stage);
    return 0;
}

void *dancer(int me) {
    int stageSlot = -1;
    int limit = 6;
    pthread_mutex_lock(&the_mutex);

    while ((howMany(JUGGLER) || howMany(SOLOIST)) || emptyStageChecker() < 0 || dancerHistory>limit) {
        //printf("Juggler cond: %d\nSoloist cond: %d\nempty cond:%d\n",howMany(JUGGLER),howMany(SOLOIST),emptyStageChecker());
        //printf("\t\t Waiting dancer... %lu\n", pthread_self());
        pthread_cond_wait(&condD, &the_mutex);
    }
    if (!(howMany(DANCER))) { //first dancer
        stageSlot = emptyStageChecker();
        stage[stageSlot] = DANCER;
        //TODO: counter shit
        dancerHistory++;
        //printf("dancerHistory++ = %d\n", dancerHistory);
    } else {
        stageSlot = emptyStageChecker();
        stage[stageSlot] = DANCER;
        dancerHistory++;
        //printf("dancerHistory++ = %d\n", dancerHistory);
    }
    printf("[Dancer #%d]: Got in stage slot %d\n",me, stageSlot);
    performerCounter++;
    int timeToSleep = rand() % 2 + 1;
    // printf("I am a dancer:%lu getting in slot %d for %d\n", pthread_self(), stageSlot + 1, timeToSleep);
    printStage(stage);
    pthread_mutex_unlock(&the_mutex);

    sleep(timeToSleep);

    pthread_mutex_lock(&the_mutex);
    printf("[Dancer #%d]: Got off stage slot %d\n",me, stageSlot);
    if (dancerHistory <= limit) {
        if (!anyOthers(DANCER)) {//last and under limit
            stage[stageSlot] = EMPTY;
            pthread_cond_broadcast(&condS);
            pthread_cond_broadcast(&condJ);
            pthread_cond_broadcast(&condD);
            printStage(stage);
            pthread_mutex_unlock(&the_mutex);
        } else { //not last and under limit
            stage[stageSlot] = EMPTY;
            pthread_cond_broadcast(&condD);
            printStage(stage);
            pthread_mutex_unlock(&the_mutex);
            //printf("not last and under limit\n");
        }
    } else {
        if (!anyOthers(DANCER)) {//last and over limit
            printf("[Dancer #%d]: That's enough dancing for now.\n",me);
            stage[stageSlot] = EMPTY;
            pthread_cond_broadcast(&condS);
            pthread_cond_broadcast(&condJ);
            printStage(stage);
            dancerHistory = 0;
            //printf("Resetting dancerHistory\n");
            pthread_mutex_unlock(&the_mutex);
        } else {//not last and over limit
            stage[stageSlot] = EMPTY;
            printStage(stage);
            pthread_mutex_unlock(&the_mutex);
        }
    }

    pthread_exit(0);
}

void *juggler(int me) {
    int limit = 4;
    int stageSlot = -1;
    pthread_mutex_lock(&the_mutex);
    //printf("Dancer cond: %d\nSoloist cond: %d\nempty cond:%d\n",howMany(DANCER),howMany(SOLOIST),emptyStageChecker());
    while ((howMany(DANCER) || howMany(SOLOIST)) || emptyStageChecker() < 0 || jugglerHistory > limit) {
        //printf("Dancer cond: %d\nSoloist cond: %d\nempty cond:%d\n",howMany(DANCER),howMany(SOLOIST),emptyStageChecker());
        //printf("\t\t Waiting juggler...\n");
        pthread_cond_wait(&condJ, &the_mutex);
    }
    if (!howMany(JUGGLER)) {
        stageSlot = emptyStageChecker();
        stage[stageSlot] = JUGGLER;
        //TODO: counter shit
        jugglerHistory++;
        //printf("jugglerHistory++ = %d\n", jugglerHistory);
    } else {
        stageSlot = emptyStageChecker();
        stage[stageSlot] = JUGGLER;
        jugglerHistory++;
        //printf("jugglerHistory++ = %d\n", jugglerHistory);
    }
    printf("[Juggler #%d]: Got in stage slot %d\n",me, stageSlot);
    performerCounter++;
    int timeToSleep = rand() % 3 + 1;
    //printf("I am a juggler:%lu getting in slot %d for %d\n", pthread_self(), stageSlot + 1, timeToSleep);
    printStage(stage);
    pthread_mutex_unlock(&the_mutex);

    sleep(timeToSleep);

    pthread_mutex_lock(&the_mutex);
    printf("[Juggler #%d]: Got off stage slot %d\n",me, stageSlot);
    if (jugglerHistory <= limit) {
        if (!anyOthers(JUGGLER)) { // last and under limit
            stage[stageSlot] = EMPTY;
            pthread_cond_broadcast(&condS);
            pthread_cond_broadcast(&condJ);
            pthread_cond_broadcast(&condD);
            printStage(stage);
            pthread_mutex_unlock(&the_mutex);
        } else { //not last and under limit
            stage[stageSlot] = EMPTY;
            pthread_cond_broadcast(&condJ);
            printStage(stage);
            pthread_mutex_unlock(&the_mutex);
        }
    } else {
        if (!anyOthers(JUGGLER)) {//last and over limit
            printf("[Juggler #%d]: That's enough juggling for now.\n",me);
            stage[stageSlot] = EMPTY;
            pthread_cond_broadcast(&condS);
            pthread_cond_broadcast(&condD);
            printStage(stage);
            jugglerHistory = 0;
            //printf("Resetting jugglerHistory\n");
            pthread_mutex_unlock(&the_mutex);
        } else {//not last and over limit
            stage[stageSlot] = EMPTY;
            printStage(stage);
            pthread_mutex_unlock(&the_mutex);
        }
    }

    pthread_exit(0);
}

void *soloist(int me) {
    int stageSlot = -1;
    int timeToSleep = rand() % 4 +1;
    pthread_mutex_lock(&the_mutex);
    while(howMany(JUGGLER) || howMany(DANCER) || howMany(SOLOIST)){
        //printf("Dancer cond: %d\nJuggler cond: %d\nSoloist cond:%d\n",howMany(DANCER),howMany(JUGGLER),howMany(SOLOIST));
        //printf("Waiting soloist...\n");
        pthread_cond_wait(&condS,&the_mutex);
    }
    performerCounter++;
    stageSlot = emptyStageChecker();
    stage[stageSlot] = SOLOIST;
    printf("[Soloist #%d]: Got in stage slot %d\n",me,stageSlot);
    printStage(stage);
    pthread_mutex_unlock(&the_mutex);
    sleep(timeToSleep);
    pthread_mutex_lock(&the_mutex);
    stage[stageSlot] = EMPTY;
    printf("[Soloist #%d]: That's enough solo performances for now.\n",me);
    pthread_cond_broadcast(&condD);
    pthread_cond_broadcast(&condJ);
    printStage(stage);
    if(performerCounter==24){
        pthread_cond_broadcast(&condS);
    }
    pthread_mutex_unlock(&the_mutex);
    printf("[Soloist #%d]: Got off stage slot %d\n",me ,stageSlot);
    pthread_exit(0);
}

//HELPERS

int howMany(int me) { //returns (int) number of specified performers ON stage
    pthread_mutex_lock(&how_mutex);
    int count = 0;
    for (int i = 0; i < 4; ++i) {
        if (stage[i] == me) {
            count++;
        }
    }
    pthread_mutex_unlock(&how_mutex);
    return count;
}

int anyOthers(int me) { //returns (int) number of how many OTHERS of the same performer type are on stage
    return howMany(me) - 1;
}

int emptyStageChecker() {
    for (int i = 0; i < 4; ++i) {
        if (EMPTY == stage[i]) {
            return i;
        }
    }
    return -1;
}

void printStage(int *stage) {
    printf("\t\t\t{ ");
    for (int i = 0; i < 4; ++i) {
        switch (stage[i]) {
            case 0:
                printf("EMPTY ");
                break;
            case 1:
                printf("DANCER ");
                break;
            case 2:
                printf("JUGGLER ");
                break;
            case 3:
                printf("SOLOIST ");
                break;
            default:
                printf(" CRAIG SHUE ");
                break;
        }

    }
    printf("}\n");
}