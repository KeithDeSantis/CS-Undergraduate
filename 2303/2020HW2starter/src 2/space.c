/*
 * space.c
 *
 *  Created on: Jan 27, 2020
 *      Author: Therese
 */
#include <stdlib.h>
#include "stdio.h"
#include "space.h"
#include "marker.h"




bool initSpace(int* corner, int howManyRows)
{
	bool ok = true;
	for(int row = 0; row< howManyRows; row++)
	{
		for(int col = 0; col < howManyRows; col++)
		{
			*(corner+row*howManyRows + col) = 0;
		}
	}
	return ok;
}

void printSpace(int* corner, int x, int y){
    int* index = corner;
    while(index-corner < x * y){
        printf("%x ", *index);
        index++;
        if((index - corner) % 20 == 0){
            printf("\n");
        }
    }
}
Marker* set(int* corner, int x, int y, int indexNum){
    Marker* mark = placeMarker(x,y, indexNum);
    *(corner + x + y*20) = mark->index%10 +1;
    return mark;
}



