#include<iostream>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<string>
#include<sstream>

using namespace std;

class Data{
public:
    Data(int dia, int mes, int ano);
    void setData(int dia, int mes, int ano);
    void getData();
private:
    int dia;
    int mes;
    int ano;
};

Data :: Data (int dia, int mes, int ano)
{
    setData(dia, mes, ano);
}

void Data :: setData(int dia, int mes, int ano)
{
    int diasmes[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    if (mes > 0 && mes < 13)
    {
        this->mes  = mes;
        // validando o dia de acordo com o mes
        if(dia > 0 && dia <= diasmes[mes+1])
        {
            this->dia = dia;
        }
        else
        {
            cout << "Dia invalido" << endl;
            this->dia = 1;
        }
    }
    else
    {
        cout << "Mês invalido" << endl;
        this->mes = 1;
    }
    if (ano > 0)
    {
        this->ano = ano;
    }
    else
    {
        cout << "Ano invalido" << endl;
        this->ano = 1900;
    }
}

void Data :: getData()
{
    cout << this->dia << "/" << this->mes << "/" << this->ano;
    return;
}

