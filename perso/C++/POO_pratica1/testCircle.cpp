#include <iostream>
#include <math.h>
#include <stdlib.h>
#include <stdio.h>
#define PI 3.14
#include "Circulo.h"
#include "Point.h"

using namespace std;

class TestCircle{

};

int main (){
    Circle* c1 = new Circle();
    Circle* c2 = new Circle();
    c1->setRadius(4.0);
    c2->setRadius(5.0);
    cout << "Area c1:" << c1->area() << endl;
    cout << endl;
    cout << "Area c2:" << c2->area() << endl;
    Circle* c3 = new Circle(1.0,2.0,3.0);
    Circle* c4 = new Circle(2.0,3.0,6.0);
    cout << endl;
    cout << "Area c3:" << c3->area() << endl;
    cout << endl;
    cout << "Area c4:" << c4->area() << endl;
    cout << endl;

    Circle* circles[10];
    int i = 0;
    for (i = 0; i < 10; i++) {
      circles[i] = new Circle();
   }


   Point* p1 = new Point();
   Point* p2 = new Point();
   p1->desloca(1.0,1.0);
   p2->desloca(2.0,2.0);
   cout << "coordenadas de p1: x = " << p1->x << "y = " << p1->y << endl;
   cout << "coordenadas de p1: x = " << p2->x << "y = " << p2->y << endl;
   p1->distancia(p1->x, p1->y, p2->x, p2->y);
   return 0;
}
