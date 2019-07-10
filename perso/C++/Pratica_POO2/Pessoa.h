#include<iostream>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<string>
#include<sstream>
#include "Data.h"


using namespace std;

class Pessoa{

public:
    void setNome(string nome);
    void setCpf(string cpf);
    void setSexo(string sexo);
    string getNome();
    string getCpf();
    string getSexo();
    void apresentarPessoa();
    Pessoa(string nome, string cpf, string sexo);

private:
    string nome;
    string cpf;
    string sexo;


};

Pessoa :: Pessoa(string nome, string cpf, string sexo)
{
    this->nome = nome;
    this->cpf = cpf;
    this->sexo = sexo;
}

void Pessoa :: setNome(string nome)
{
    this->nome = nome;
}

void Pessoa :: setCpf(string cpf)
{
    this->cpf = cpf;
}
void Pessoa :: setSexo(string sexo)
{
    this->sexo = sexo;
}

string Pessoa :: getNome()
{
    return this->nome;
}

string Pessoa :: getCpf()
{
    return this->cpf;
}

string Pessoa :: getSexo()
{
    return this->sexo;
}

void Pessoa :: apresentarPessoa()
{
    cout << "nome: " << this->nome << endl;
    cout << "cpf: " << this->cpf << endl;
    cout << "sexo: " << this->sexo << endl;
}


