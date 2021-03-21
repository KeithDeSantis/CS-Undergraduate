/*
 * marker.c
 *
 *  Created on: Jan 27, 2020
 *      Author: Therese
 */

#include "marker.h"
#include <stdlib.h>
#include <stdio.h>


Marker* placeMarker(int r, int c, indexNum)
{
	Marker* mP = (Marker*) malloc(sizeof(Marker));
	mP->col=c;
	mP->row=r;
	mP->index=indexNum;

	return mP;
}

