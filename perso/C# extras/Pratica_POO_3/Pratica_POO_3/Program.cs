using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Collections;


namespace Pratica_POO_3
{
    class Person
    {
        protected String name;
        public Person()
        {
            Console.WriteLine("Construtor de Person");
            name = "Fulano";
        }
        public String getName()
        {
            return name;
        }
        public void setName(String name)
        {
            this.name = name;
        }
    }

    class Student : Person
    {
        protected string course;
        public Student()
        {
            Console.WriteLine("Construtor de Student");
            course = "CC";
        }

        public Boolean testName(char c)
        {
            Boolean comp = false;
            char [] cmp  = this.name.ToCharArray(0, 1);
            if(cmp[0] == c)
            {
                comp = true;
            }
            return comp;
        }

    }

    class PhdStudent : Student
    {
        public PhdStudent()
        {
            Console.WriteLine("Construtor de PhdStudent");
        }
    }

class Program
    {
        static void Main(string[] args)
        {
            Person p = new Person();
            Student s = new Student();
            List<Object> lis = new List<Object>();
            lis.Add(p);
            lis.Add(s);
            s.setName("Beltrano");
            foreach (Person obj in lis) {
                Console.WriteLine(obj.getName());
            }
            Student std = new Student();
            std.setName("Harry");
            Console.WriteLine(std.getName());
            if (std.testName('H'))
            {
                Console.WriteLine("True");
            }
            else
            {
                Console.WriteLine("False");
            }

            Console.WriteLine();
            Console.WriteLine();
            PhdStudent phd = new PhdStudent();

            Console.ReadKey();
        }
    }
}
