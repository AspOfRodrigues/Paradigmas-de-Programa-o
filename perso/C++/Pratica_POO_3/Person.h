#include <iostream>
#include <string>
#include <string.h>

using namespace std;

class Person {
protected:
       string name;
public:
       Person();
       string getName();
       void setName(string name);
};

class Student : public Person{
protected:
        string course;

public:
        Student();
        bool testName(char c);

};

class PhdStudent : public Student{
public:
    PhdStudent();

};

PhdStudent :: PhdStudent (){
    cout << "construtor de PhdStudent" << endl;
}

bool Student :: testName(char c) {
  return name[0] == c;
}

Student :: Student ()
{
    cout << "Construtor de student" << endl;

}

Person :: Person()
{
    cout << "Construtor de Person" << endl;
    name = "Fulano";
}

string Person :: getName()
{
    return this->name;
}

void Person :: setName(string name)
{
    this->name = name;
}


