#include <stdio.h>

int main() {
    char* string[5] = {"zero","one","two","three","four"};

    FILE* file = fopen("LAB4.txt", "w+");
    fprintf(file,"%s", string[1]);
    fclose(file);
    return 0;
}
