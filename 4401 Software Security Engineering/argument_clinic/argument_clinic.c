#include <stdio.h>
#include <string.h>

int main(int argc, char** argv) {
    printf("You have entered the following: ");
    printf(argv[1]);
    printf("\n");
    
    if (strchr(strtok(argv[1], " "), 0) == argv[1]) {
        printf("Way to enter nothing, loser.\n");
    } else {
        printf("You're still lame.\n");
    }
}
