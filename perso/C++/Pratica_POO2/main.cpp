#include <iostream>
//#include "Data.h"
#include "Pessoa.h"
using namespace std;

int main()
{
    cout << "Hello world!" << endl;
    Data* dataNascimento  = new Data(29, 07, 1999);
    dataNascimento->setData(40,7,1999);
    //cout << "Data de nascimento:" << dataNascimento->dia << "/" << dataNascimento->mes << "/" << dataNascimento->ano << endl;
    //dataNascimento->dia = 40;
    cout << "Data de nascimento:";
    dataNascimento->getData();
    cout << endl;
    Pessoa* asp = new Pessoa("Henri", "0245632103", "Masculino");
    asp->apresentarPessoa();


    return 0;
}
