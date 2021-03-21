#include <iostream>

using namespace std;

#include "Tests.h"
#include "Production.h"
int main(int argc, char* argv[]) {
    cout << "HOMEWORK 5" << endl;
    Tests* tsp = new Tests();

    if(tsp->tests()){
        Production* p = new Production();
        p->prod(argc,argv);
        puts("Production passed");
    }else{
        puts("Failed");
    };

    return 0;
}
