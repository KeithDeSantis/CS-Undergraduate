//
// Created by keith desantis  on 2/1/21.
//
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <wait.h>

int main()
{
    char c[1000];
    FILE* seedFile = fopen("seed.txt","r");
    fscanf(seedFile,"%[^\n]",c);
    int seed = atoi(c);
    srand(seed);
    printf("Read seed value: %d\n",seed);
    printf("It’s time to see the world/file system!\n");
    char* sites[6] = { "/home", "/proc", "/proc/sys", "/usr", "/usr/bin", "/bin" };

    //Getting our random stuff
    int visited[6] = { -1,-1,-1,-1,-1,-1 };
    int visits = 0;

    while (visits != 6) // Loop for each change of directory
    {
        int badTarget = 1; // Bool for if we have a valid target
        int target; // Target index initialization
        while (badTarget) // Loop until a good target is found
        {
            target = rand() % 6; // Generate a target
            int alreadyVisited = 0; // Boolean for if our target has already been visited
            for(int j = 0; j < 6; j++) // Check the visited list
            {
                if(visited[j] == target)
                { alreadyVisited = 1; }
            }
            if(!alreadyVisited) badTarget = 0; // Determine if target is valid
        }
        visited[target] = target; //Now we have our target and record it

        //Change directory
        char s[100];
        chdir(sites[target]);
        printf("Selection #%d: %s [SUCCESS]\n", visits+1, sites[target]);
        printf("Current reported directory: %s\n", getcwd(s, 100));

        int childPID = fork();

        if(childPID == 0)
        {
            printf("    [Child, PID: %d]: Executing 'ls -alh' command...\n", getpid());
            char *cmd = "ls";
            char *argv[3] = { "ls", "-alh", NULL};
            execvp(cmd, argv);
            exit(0);
        }
        else {
            int status;
            printf("[Parent]: I am waiting for PID %d to finish.\n", childPID);
            waitpid(childPID, &status, 0);
            printf("[Parent]: Child %d finished with status code 0. Onward!\n",childPID);
        }
        visits++;
    }


    return 0;
}
