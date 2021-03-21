//
// Created by kushshah on 2/5/21.
//
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <wait.h>
#include <time.h>
#include <stdbool.h>
#include <math.h>


int main(){
    struct timespec start, stop;
    int ppid = getpid();
    int ID[4] = {-2,-2,-2,-2};
    int pCount = 0;
    int f = 0;
    for (int i = 0; i < 4; ++i) {
        if(getpid()==ppid) {
            pCount++;
            clock_gettime(1,&start);
            f = fork();
            if(getpid()==ppid){
                ID[i] = f;
                printf("[Parent]: I forked off child %d\n",ID[i]);
            }
        }
    }

        if(f == 0){
            printf("\t[Child, PID: %d]: Executing './slug %d' command...\n",getpid(),pCount);
            char *cmd = "./slug";
            char fileNum[1000];
            sprintf(fileNum,"%d",pCount);
            char *argv[3] = { "./slug", fileNum, NULL};
            execvp(cmd, argv);
        }else{
            bool allDone = false;

            while (!allDone) {
                int status = -14;
                int pid = waitpid(WAIT_ANY,&status,WNOHANG);
                usleep(250000);
                printf("The race is ongoing. The following children are still racing: ");
                for (int i = 0; i < 4; ++i) {
                        if(ID[i] > 0){
                            printf("%d ",ID[i]);
                        }
                }
                printf("\n");
                if(status != -14){
                    clock_gettime(1,&stop);
                    printf("Child %d finished! It took %f!\n",pid,(stop.tv_sec + (stop.tv_nsec)/pow(10,9)) - (start.tv_sec + (start.tv_nsec)/pow(10,9)));
                    for (int j = 0; j < 4; ++j) {
                        if(pid == ID[j]){
                            ID[j] = -1;
                        }
                    }
                }
                int count = 0;
                for (int j = 0; j < 4; ++j) {
                    count += ID[j];
                }
                if(count == -4){
                    allDone = true;
                }
            }
        }

    clock_gettime(1,&stop);
    printf("The race is over! It took %f!\n",(stop.tv_sec + (stop.tv_nsec)/pow(10,9)) - (start.tv_sec + (start.tv_nsec)/pow(10,9)));

    return 0;
}