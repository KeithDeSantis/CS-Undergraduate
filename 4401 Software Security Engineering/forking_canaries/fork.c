#include <signal.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int numBytes;
char* largeBuffer;

void win()
{
    char buffer[105];
    FILE *fp;

    fp  = fopen("./flag.txt", "r");
    fgets(buffer, 65, (FILE*) fp);
    printf("flag: %s\n", buffer);
    exit(0);
}

void handle_client()
{
    char buf[{{buff_size}}];
    largeBuffer = malloc(21);
    printf("Here's your fork!\n");
    printf("How many bites? "); 
    fflush(stdout);
    fgets(largeBuffer, 20, stdin);
    sscanf(largeBuffer, "%d", &numBytes);
    largeBuffer = malloc(1000);
    printf("Bites: ");
    fflush(stdout);
    fgets(largeBuffer, 999, stdin);
    memcpy(buf, largeBuffer, numBytes);

    return;
}

int serve()
{
    int pid;

    // Prevent pesky zombies
    signal(SIGCHLD, SIG_IGN);
    for(;;)
    {
        if ((pid = fork()) == -1)
        {
            continue;
        }
        else if(pid > 0)
        {
            int status;
            wait(&status);
            pid = 0;
            continue;
        }
        else if(pid == 0)
        {   
            handle_client();
            printf("Done\n");
            exit(0);
        }
    }
    return 0;
}

int main()
{
    return serve();
}
