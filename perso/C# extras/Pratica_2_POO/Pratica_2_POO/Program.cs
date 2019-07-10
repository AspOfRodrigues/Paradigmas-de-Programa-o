using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Pratica_2_POO
{
    class Data
    {
        private int dia;
        private int mes;
        private int ano;

        public Data (int dia, int mes, int ano)
        {
            this.dia = dia;
            this.mes = mes;
            this.ano = ano;
        }

        public void setData(int dia, int mes, int ano)
        {
            int[] diasmes = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
            if (mes > 0 && mes < 13)
            {
                this.mes = mes;
                // validando o dia de acordo com o mes
                if (dia > 0 && dia <= diasmes[mes + 1])
                {
                    this.dia = dia;
                }
                else
                {
                    Console.WriteLine("Dia inválido");
                    this.dia = 1;
                }
            }
            else
            {
                Console.WriteLine("Mês inválido");
                this.mes = 1;
            }

            if (ano > 0)
            {
                this.ano = ano;
            }
            else
            {
                Console.WriteLine("Ano inválido");
                this.ano = 1900;
            }
        }

        public string getData()
        {
            return this.dia + "/" + this.mes + "/" + this.ano;
        }
    }

    class Pessoa
    {
        public string nome;
        public string cpf;
        public string sexo;
        public Data dataNasc;

        public Pessoa(string nome, string cpf, string sexo)
        {
            this.nome = nome;
            this.cpf = cpf;
            this.sexo = sexo;
        }

        public string getNome()
        {
            return this.nome;
        }

        public string getCpf()
        {
            return this.cpf;
        }

        public string getSexo()
        {
            return this.sexo;
        }
        
        public void setNome(string nome)
        {
            this.nome = nome;
        }

        public void setCpf(string cpf)
        {
            this.cpf = cpf;
        }

        public void setSexo(string sexo)
        {
            this.sexo = cpf;
        }

        public void apresentarPessoa()
        {
            Console.WriteLine("nome:" + this.nome);
            Console.WriteLine("cpf:" + this.cpf);
            Console.WriteLine("sexo:" + this.sexo);
        }
    }



    class Program
    {
        static void Main(string[] args)
        {
            Data dataNascimento = new Data(29,07,1999);
            //Console.WriteLine("Data de Nascimento:" + dataNascimento.dia + "/" + dataNascimento.mes + "/" + dataNascimento.ano);
            //dataNascimento.dia = 40;
            dataNascimento.setData(29, 07, 1999);
            Console.WriteLine("Data de Nascimento:" + dataNascimento.getData());

            Pessoa asp = new Pessoa("Alexios", "0123048204", "Masculino");
            asp.apresentarPessoa();

            Console.ReadKey();
        }
    }
}
