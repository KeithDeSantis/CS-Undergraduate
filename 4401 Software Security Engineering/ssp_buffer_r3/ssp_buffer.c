#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define SMALL_BUFF_SIZE 10


void win()
{
  char buffer[64];
  FILE *fp;

  puts("code flow successfully changed.");
  
  fp  = fopen("flag.txt", "r");
  fgets(buffer, 64, fp);
  printf("flag: %s\n", buffer );
}


void a() {
    char buffer[96];
    printf("a entered\n");
    gets(buffer);
}

void b() {
    char buffer[160];
    printf("b entered\n");
    gets(buffer);
}

void c() {
    char buffer[160];
    printf("c entered\n");
    gets(buffer);
}

void d() {
    char buffer[160];
    printf("c entered\n");
    gets(buffer);
}

void e() {
    char buffer[160];
    printf("e entered\n");
    gets(buffer);
}

void f() {
    char buffer[160];
    printf("f entered\n");
    gets(buffer);
}

int main() {
    setvbuf(stdout, NULL, _IONBF, 0);
    printf("Address of win: %p\n", win);

    char buffer[17];
    fgets(buffer, 17, stdin);
    char small_buff[SMALL_BUFF_SIZE];

    if(fgets(small_buff, SMALL_BUFF_SIZE, stdin) == NULL){
        return -1;
    }
    small_buff[strcspn(small_buff, "\n")] = '\0';
    if(!strncmp(small_buff, "a", SMALL_BUFF_SIZE)){
        a();
    } 
    if(!strncmp(small_buff, "b", SMALL_BUFF_SIZE)){
        b();
    }
    if(!strncmp(small_buff, "c", SMALL_BUFF_SIZE)){
        c();
    }
    if(!strncmp(small_buff, "d", SMALL_BUFF_SIZE)){
        d();
    } 
    if(!strncmp(small_buff, "e", SMALL_BUFF_SIZE)){
        e();
    }
    if(!strncmp(small_buff, "f", SMALL_BUFF_SIZE)){
        f();
    }

    return 0;
}
