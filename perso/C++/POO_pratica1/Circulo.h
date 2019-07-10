#include <iostream>
#include <math.h>
#include <stdlib.h>
#include <stdio.h>
#define PI 3.14


using namespace std;



class Circle{

public:
    double area();
    void setRadius(double radius);
    Circle();
    Circle(double x, double y, double r);
private:
    double x;
    double y;
    double r;

};

Circle :: Circle ()
{
    x = 0;
    y = 0;
    r = 0;
    cout << "New\n" << endl;
}

Circle :: Circle(double x, double y, double r)
{
    this->x = x;
    this->y = y;
    this->r = r;
}

double Circle :: area()
{
    double area_circ = PI*r*r;
    return area_circ;
}

void Circle :: setRadius(double radius)
{
    this->r = radius;
    return;
}

