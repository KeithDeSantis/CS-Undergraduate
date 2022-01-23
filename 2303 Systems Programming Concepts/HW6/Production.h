/*
 * Production.h
 *
 *  Created on: Feb 1, 2020
 *      Author: Therese
 */

#ifndef PRODUCTION_H_
#define PRODUCTION_H_
#include "Variables.h"
#include "Stats.h"
#include "Cell.h"
#include "Watercraft.h"
#include "Coordinate.h"

class Production {
public:
	Production();
	virtual ~Production();
	bool prod(int argc, char* argv[]);
	int getRandomNumber(int, int);
	void systemMessage(char*);
	void checkBoundsOfCardinal(bool[], int, int);
	void welcomeScreen();
};

#endif /* PRODUCTION_H_ */
