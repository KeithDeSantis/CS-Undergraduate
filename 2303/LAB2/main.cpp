#include <iostream>
#include <stdio.h>

int main() {
    int arrayBoi[2][5] = {{1,2,3,4,5},
                          {5,6,7,8,9}};

    printf("size 1 %d \n", ((int) sizeof(arrayBoi[0]))/4);

    for (int i = 0; i < (int) sizeof(arrayBoi)/(int) sizeof(arrayBoi[0]); i++) {
        for (int j = 0; j < sizeof(arrayBoi[0])/(int) sizeof(arrayBoi[0][0]); j++) {

            arrayBoi[i][j]=1;
            printf("i: %d  j: %d value at i,j: %d\n",i,j,arrayBoi[i][j]);
        }
    }

    return 0;
}
