#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>

int main(int argc, char **argv)
{
  char buffer[{{buffsize}}];
  unsigned long ret;

  gets(buffer);

  ret =  __builtin_return_address(0);

  if((ret & 0xfffffff00000 ) != 0x7ffffff00000) {
    printf("bzzzt (%p)\n", ret);
    _exit(1);
  }
}
