#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <wait.h>

int main() {
    char c[1000];
    FILE* seedFile = fopen("seed.txt","r");
    fscanf(seedFile,"%[^\n]",c);
    int seed = atoi(c);
    srand(seed);
    printf("Read seed value: %d\n",seed);
    int num = rand() % ((15-1)-10) + 10;
    printf("Random child count:%d\n",num);
    int children[num];
    for (int i = 0; i < num; ++i) {
        children[i] = rand();
    }
    for(int i = 0; i < num; ++i) {
        int f = fork();
        if(f==0){
            int exitCode = (children[i] % 50) + 1;
            int p = getpid();
            int exitTime = (children[i] % 3) + 1;
            printf("    [Child, PID: %d]: I am the child and I will wait %d seconds and exit with code %d\n",p,exitTime,exitCode);
            sleep(exitTime);
            printf("    [Child, PID: %d]: Now exiting...\n",p);
            exit(exitCode);
        }else{
            printf("[Parent]: I am waiting for PID %d to finish.\n",f);
            int status;
            waitpid(f, &status, 0);
            printf("[Parent]: Child %d finished with status code %d. Onward!\n",f, WEXITSTATUS(status));
        }
    }
    return 0;
}
