//
// Created by kushshah on 1/30/21.
//
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <wait.h>

int main(){
    char c[1000];
    FILE* seedFile = fopen("seed.txt","r");
    fscanf(seedFile,"%[^\n]",c);
    int seed = atoi(c);
    srand(seed);
    printf("Read seed value: %d\n",seed);
    int lifeCount = rand() % ((11-1)-7) + 7;
    printf("Random Descendant Count: %d\n",lifeCount);
    printf("Time to meet the kids/grandkids/great grand kids/...\n");
    while(lifeCount >0){
        int f = fork();
        if(f ==0){
            int p = getpid();
            if(lifeCount==0){
                exit(lifeCount);
            }
            printf("\t[Child, PID: %d]: I was called with descendant count=%d. I'll have %d descendant(s)\n",p,lifeCount,lifeCount-1);
            lifeCount--;
        } else{
            int p = getpid();
            printf("[Parent, PID: %d]: I am waiting for PID %d to finish.\n",p,f);
            int status;
            waitpid(f, &status, 0);
            printf("[Parent]: Child %d finished with status code %d. It's now my turn to exit.\n",f, WEXITSTATUS(status));
            exit(WEXITSTATUS(status)+1);
        }
    }
    return 0;
}
