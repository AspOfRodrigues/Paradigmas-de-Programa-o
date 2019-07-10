#include <iostream>
#include <math.h>
#include <stdlib.h>
#include <stdio.h>
#define PI 3.14


using namespace std;



class Point{

public:
    double x, y;
    Point ();
    void desloca(double dx, double dy);
    void distancia( double x1, double y1, double x2, double y2);
};

Point :: Point()
{
    x = 0.0;
    y = 0.0;
}

void Point :: desloca(double dx, double dy)
{
    this->x += dx;
    this->y += dy;
}

void Point :: distancia( double x1, double y1, double x2, double y2)
{
    double dx = x2 - x1;
    double dy = y2 - y2;
    double distance = sqrt(dx*dx + dy*dy);
    cout << "Distancia calculada:" << distance << endl;
    return;
}
