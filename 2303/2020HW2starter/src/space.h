/*
 * space.h
 *
 *  Created on: Jan 27, 2020
 *      Author: Therese
 */

#ifndef SPACE_H_
#define SPACE_H_
#include "marker.h"

#include <stdbool.h>
bool initSpace(int*, int);
void printSpace(int* corner, int x, int y);
Marker* set(int*, int, int, int);

#endif /* SPACE_H_ */
