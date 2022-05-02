#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>

void win()
{
  char buffer[64];
  FILE *fp;

  printf("code flow successfully changed\n");
  
  fp  = fopen("./flag.txt", "r");
  fgets(buffer, 64, (FILE*)fp);
  printf("flag: %s\n", buffer );
  exit(0);
}

int main(int argc, char **argv)
{
  char buffer[{{buffsize}}];

  gets(buffer);
}

