#include <iostream>
#include "WPI_Student.h"
int main() {
    std::cout << "Hello, World!" << std::endl;
    WPI_Student* frank = new WPI_Student("Frank", 123456);
    cout << "Name: "+ frank->getName()<<endl;
    printf("ID: %d\n",frank->getID());
    puts("\nchanging information\n");
    frank->setName("Frank S");
    frank->setID(654321);
    cout << "Name: "+ frank->getName()<<endl;
    printf("ID: %d\n",frank->getID());
    delete(frank);
    return 0;
}
