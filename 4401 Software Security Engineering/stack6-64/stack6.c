#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>

char* getpath()
{
  char buffer[{{buffsize}}];
  unsigned int ret;

  printf("input path please: "); fflush(stdout);

  gets(buffer);

  printf("got path %s\n", buffer);

  return strdup(buffer);
}

int main(int argc, char **argv)
{
  getpath();
  return 0;
}
