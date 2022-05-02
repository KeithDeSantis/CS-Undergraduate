#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <err.h>

int main(int argc, char **argv)
{
  volatile int modified;
  char buffer[{{buffsize}}];
  char *variable;
  FILE *fp;

  variable = getenv("GREENIE");

  if(variable == NULL) {
      errx(1, "please set the GREENIE environment variable\n");
  }

  modified = 0;
  strcpy(buffer, variable);
  fp  = fopen("./flag.txt", "r");

  if(modified == 0x0d0a0d0a) {
      printf("you have correctly modified the variable\n");
      fgets(buffer, 64, (FILE*)fp);
      printf("flag: %s\n", buffer );
      exit(0);
  } else {
      printf("Try again, you got 0x%08x\n", modified);
  }

}
