#include <iostream>
#include "Student.h"

int main() {
    std::cout << "Hello, World!" << std::endl;
    Student* george = new Student("George", 123456789);
    cout <<"ID is:" << endl;
    cout << george->getId() <<endl;
    cout <<"Name is:" << endl;
    cout << george->getName()<<endl;
    delete(george);
    return 0;
}
