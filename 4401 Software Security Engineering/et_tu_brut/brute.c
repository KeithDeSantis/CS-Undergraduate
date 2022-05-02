#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int main (int argc, char **argv) {
	char buffer[16384];
  setbuf(stdout, NULL);
	gets(buffer);
	printf("%p\n", buffer);
}
