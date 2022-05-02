#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

int main(int argc, char **argv)
{
  volatile int modified = 0;
  char buffer[125];
  FILE *fp;

  gets(buffer);
  fp  = fopen("./flag.txt", "r");

  if(modified != 0) {
      printf("you have changed the 'modified' variable\n");
      fgets(buffer, 64, (FILE*)fp);
      printf("flag: %s\n", buffer );
  } else {
      printf("Try again?\n");
  }
}
