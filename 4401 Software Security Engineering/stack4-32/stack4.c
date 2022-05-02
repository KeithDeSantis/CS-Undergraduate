#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>

int main(int argc, char **argv)
{
  char buffer[64];
  unsigned int ret;

  gets(buffer);

  ret = __builtin_return_address(0);

  if((ret & 0xff000000) != 0xff000000) {
    printf("bzzzt (%p)\n", ret);
    _exit(1);
  }
}
