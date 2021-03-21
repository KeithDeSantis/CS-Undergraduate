/*
 * tests.c
 *
 *  Created on: Jul 4, 2019
 *      Author: Therese
 */

#include "tests.h"
#include "production.h"
#include "marker.h"

bool tests()
{
	bool answer = false;
	bool ok1 =  testSet();
	bool ok2 = testGotAdjacencyMatrix();
	bool ok3 = testSomethingElse();
	bool ok4 = testRemoveFromList();
	answer = ok1 && ok2 && ok3 && ok4;
	return answer;
}

bool testReadFile()
{
	bool ok = false;
	puts("starting testReadFile");fflush(stdout);
	//what are the criteria for success for
	//test case 1:
	//test case 2:

    if(ok)
    {
    	puts("test read file passed.");
    }
    else
    {
    	puts("test read file did not pass.");
    }
	return ok;
}

bool testGotAdjacencyMatrix()
{
	bool ans = true;
	//what are the criteria for success for
	//test case 1:
	//test case 2:

	return ans;
}

bool testSomethingElse()
{
	bool ans = true;
	//what are the criteria for success for
	//test case 1:
	//test case 2:

	return ans;
}
bool testRemoveFromList()
{
	bool ok = true;
	//what are the criteria for success for
	//test case 1:
	//test case 2:

	return ok;
}


bool testSet(){
    bool ok = false;
    int* theSpaceP = (int*) malloc(20*20*sizeof(int));
    set(theSpaceP, 3, 2,0);
    if(*(theSpaceP + 3 + 2*20)){
        ok = true;
    }
    return ok;
}




