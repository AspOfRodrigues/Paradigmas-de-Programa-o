using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Pratica_1_POO
{
    public class Circle
    {
        private double x;
        private double y;
        private double r;

        public Circle()
        {
            x = 0.0;
            y = 0.0;
            r = 0.0;
            Console.WriteLine("new Circle");
        }

        public Circle(double x, double y, double r)
        {
            this.x = x;
            this.y = y;
            this.r = r;
            Console.WriteLine("new Circle");
        }

        public double area()
        {
            return Math.PI * r * r;
        }
        public void setRadius(double radius)
        {
            this.r = radius;
        }
    }

    public class TestCircle
    {
        static void main(String[] args)
        {
            Circle c1 = new Circle();
            Circle c2 = new Circle();
            Console.WriteLine("Area c1: " + c1.area());
            Console.WriteLine("Area c2: " + c2.area());
        }
    }


    public class Point
    {
        public double x;
        public double y;

        public Point()
        {
            this.x = 0.0;
            this.y = 0.0;
        }

        public void desloca (double dx, double dy)
        {
            this.x = x + dx;
            this.y = y + dy;
        }

        public static double distancia(double x1, double y1, double x2, double y2)
        {
            double dx = x2 - x1;
            double dy = y2 - y1;
            double distancia = Math.Sqrt(dx * dx + dy * dy);
            return distancia;
        }
    }

    public class Program
    {
        static void Main(string[] args)
        {
            Circle c = new Circle();
            c.setRadius(5.0);
            Console.WriteLine("Area do circulo = " + c.area());
            Circle c2 = new Circle();
            c2.setRadius(5.0);
            Circle new_c1 = new Circle(1.0, 1.0, 4.0);
            Circle new_c2 = new Circle(2.0, 2.0, 6.0);
            Console.WriteLine("Area do circulo new_c1 =" + new_c1.area());
            Console.WriteLine("Area do circulo new_c2 =" + new_c2.area());
            Circle[] circs = new Circle[10];
            for(int i = 0; i < circs.Length; i++)
            {
                circs[i] = new Circle();
            }
            Point p1 = new Point();
            Point p2 = new Point();

            p1.desloca(1.0, 1.0);
            p2.desloca(2.0, 2.0);

            double dist = Point.distancia(p1.x, p1.y, p2.x, p2.y);

            Console.WriteLine("Distancia entre p1 e p2 = " + dist);
            Console.ReadKey();
        }
    }
}
