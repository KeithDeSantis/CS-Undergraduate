//
// Created by kushshah on 2/2/21.
//
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <wait.h>


int main(int argc, char *argv[]){
    if(argc != 2){
        printf("Wrong number of arguments. \n\tYou gave: %d\n\tShould be 1 \nProfessor you dirty dog you.\n", argc-1);
        exit(0);
    }
    int fileNum = atoi(argv[1]);
    printf("Reading file number - %d\n", fileNum);
    char files[4][1000] = {"seed_slug_1.txt",
                           "seed_slug_2.txt",
                           "seed_slug_3.txt",
                           "seed_slug_4.txt",};
    FILE* pickedFile = fopen(files[fileNum-1],"r");
    char c[1000];
    fscanf(pickedFile,"%[^\n]",c);
    int seed = atoi(c);
    srand(seed);
    printf("[Slug PID: %d]: Read seed value: %d\n",getpid(),seed);
    int seconds = rand() % ((5-1)-1) + 1;
    int flip = rand() % 2;
    printf("[Slug PID: %d]: Delay time is %d seconds, and the coin flip is %d\n",getpid(),seconds,flip);
    printf("[Slug PID: %d]: I'll get to it eventually...\n",getpid());
    sleep(seconds);
    if(flip){
        char *cmd = "id";
        char *argv[3] = { "id", "-u", NULL};
        printf("[Slug PID: %d]: Break time is over! I am running the ’id -u’ command.\n",getpid());
        execvp(cmd, argv);

    }else{
        char *cmd = "last";
        char *argv[4] = { "last", "-d", "--fulltimes",NULL};
        printf("[Slug PID: %d]: Break time is over! I am running the ’last -d --fulltimes' command.\n",getpid());
        execvp(cmd, argv);
    }
}
