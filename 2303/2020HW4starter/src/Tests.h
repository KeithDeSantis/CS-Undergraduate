/*
 * Tests.h
 *
 *  Created on: Feb 1, 2020
 *      Author: Therese
 */

#ifndef TESTS_H_
#define TESTS_H_

#include "Production.h"

class Tests {
public:
	Tests();
	virtual ~Tests();
	bool tests();
	bool testReadFile();
	bool testEnqueue();
	bool testMakeLList();
	bool testRemoveFromList();
	bool testPrintHistory();
	bool testWriteFile();
};

#endif /* TESTS_H_ */
