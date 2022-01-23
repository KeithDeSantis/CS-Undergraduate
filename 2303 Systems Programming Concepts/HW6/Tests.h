/*
 * Tests.h
 *
 *  Created on: Feb 1, 2020
 *      Author: Therese
 */

#ifndef TESTS_H_
#define TESTS_H_

class Tests {
public:
	Tests();
	virtual ~Tests();
	bool tests();
	bool testSetNumHits();
	bool testSetNumMisses();
	bool testGetNumHits();
	bool testGetNumMisses();
	bool testSetRow();
	bool testSetColumn();
	bool testGetRow();
	bool testGetColumn();
	bool testSetSymbol();
	bool testSetPosition();
	bool testGetSymbol();
	bool testGetPosition();
	bool testSetSymbolWatercraft();
	bool testSetLength();
	bool testSetName();
	bool testGetSymbolWatercraft();
	bool testGetLength();
	bool testGetName();
	bool testCheckSunkedShip();
	bool testGetTotalShots();
	bool testGetHitMissRatio();
	bool testIsWinner();
	bool testIsDigit();
	bool testConvertStringToPosition();
	bool testCheckBoundsOfCardinal();
	bool testGeneratePosition();
	bool testPutShipOnBoard();
	bool testRandomlyPutShipsOnGameBoard();
	bool testInitializeGameBoard();
	bool testCheckShot();

};

#endif /* TESTS_H_ */
