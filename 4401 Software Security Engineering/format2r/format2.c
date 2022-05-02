#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>

int target;

void vuln()
{
  char buffer[512];
  char flagBuffer[64];
  FILE *fp;

  fgets(buffer, sizeof(buffer), stdin);
  printf(buffer);

  fp = fopen("./flag.txt", "r");
  
  if(target == 64) {
      printf("you have modified the target :)\n");

      fgets(flagBuffer, 64, (FILE*)fp);
      printf("flag: %s\n", flagBuffer);
  } else {
      printf("target is %d :(\n", target);
  }
}

int main(int argc, char **argv)
{
  vuln();
}
