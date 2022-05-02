#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>

int target;

void vuln(char *string)
{
  char flagBuffer[64];
  FILE *fp;

  printf(string);

  if(target) {
    printf("you have hit the target correctly :)\n");
    fp = fopen("./flag.txt", "r");
    fgets(flagBuffer, 64, (FILE*)fp);
    printf("flag: %s\n", flagBuffer);
  }
}

int main(int argc, char **argv)
{
  vuln(argv[1]);
}