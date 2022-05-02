#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>

void vuln(char *string)
{
  volatile int target;
  char buffer[64];
  char flagBuffer[64];
  FILE *fp;

  target = 0;

  sprintf(buffer, string);
  
  if(target == 0xdeadbeef) {
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
