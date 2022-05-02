#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <sys/types.h>

struct data {
  char name[76];
};

struct fp {
  void (*fp)();
};

void winner()
{
  char buffer[64];
  FILE *fp;

  printf("Level passed\n");

  fp = fopen("./flag.txt", "r");

  fgets(buffer, 64, (FILE*)fp);
  printf("flag: %s\n", buffer);
}

void nowinner()
{
  printf("level has not been passed\n");
}

int main(int argc, char **argv)
{
  struct data *d;
  struct fp *f;

  d = malloc(sizeof(struct data));
  f = malloc(sizeof(struct fp));
  f->fp = nowinner;

  printf("data is at %p, fp is at %p\n", d, f);

  strcpy(d->name, argv[1]);

  f->fp();
}
