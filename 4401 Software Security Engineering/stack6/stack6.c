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

  ret = __builtin_return_address(0);

  if((ret & 0xf0000000) == 0xf0000000) {
      printf("bzzzt (%p)\n", ret);
      _exit(1);
  }

  printf("got path %s\n", buffer);

  return strdup(buffer);
}

int main(int argc, char **argv)
{
  getpath();
  return 0;
}
