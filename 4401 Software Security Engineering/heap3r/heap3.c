#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <stdio.h>
#include "fun_malloc.h"

void winner()
{
  char buffer[64];
  FILE *fp;

  printf("that wasn't too bad now, was it? @ %d\n", time(NULL));

  fp = fopen("./flag.txt", "r");

  fgets(buffer, 64, (FILE*)fp);
  printf("flag %s\n", buffer);
}

int main(int argc, char **argv)
{
  char *a, *b, *c;

  a = funMalloc(32);
  b = funMalloc(32);
  c = funMalloc(32);

  strcpy(a, argv[1]);
  strcpy(b, argv[2]);
  strcpy(c, argv[3]);

  funFree(c);
  funFree(b);
  funFree(a);

  printf("dynamite failed?\n");
}
