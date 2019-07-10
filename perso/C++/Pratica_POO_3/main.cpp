#include <iostream>
#include "Person.h"
#include <vector>

using namespace std;

int main()
{
    Person* p = new Person();
    Student* s = new Student();
    vector <Person> lis;
    lis.push_back(*p) ;
    lis.push_back(*s);

    s->setName("Beltrano");
    //for (Person x: lis){ // Descomente caso seu C++ suportar
      //cout << x.getName() << endl;
    //}
    cout << p->getName() << endl;
    if(s->testName('B')){
        cout << "true" << endl;
    }else{
        cout << "false" << endl;
    }

    PhdStudent* phd = new PhdStudent();
}
