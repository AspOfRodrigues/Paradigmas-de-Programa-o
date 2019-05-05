%______t4_________

%_____rico_____

rico(henrique).
rico(caren).
rico(alice).
rico(adriano).

%_____pobre_____

pobre(maria).
pobre(bernardo).
pobre(bia).
pobre(pedro).


%_____vitima_____

vitima(anita).

%_____dias_____

dia(segunda).
dia(terca).
dia(quarta).
dia(quinta).
dia(sexta).
dia(sabado).
dia(domingo).

%_____ciume_____

ciume(caren).
ciume(alice).


%_____cidade_____

cidade(santamaria).
cidade(portoalegre).

%_____insano_____

insano(maria).
insano(adriano).

%_____dia_do_crime_____

crime_day(quinta).
crime_day(sexta).

%_____probabilidades_____

%_____Pedro_____

probabilidade(pedro,santamaria,segunda).
probabilidade(pedro,santamaria,terca).
probabilidade(pedro,portoalegre,quarta).
probabilidade(pedro,santamaria,quinta).
probabilidade(pedro,apartamento,sexta).

%_____Caren_____

probabilidade(caren,portoalegre,segunda).
probabilidade(caren,portoalegre,terca).
probabilidade(caren,portoalegre,quarta).
probabilidade(caren,santamaria,quinta).
probabilidade(caren,apartamento,sexta).

%_____Henrique_____

probabilidade(henrique,apartamento,segunda).
probabilidade(henrique,portoalegre,terca).
probabilidade(henrique,apartamento,quarta).
probabilidade(henrique,apartamento,quinta).
probabilidade(henrique,apartamento,sexta).

%_____Bia_____

probabilidade(bia,apartamento,segunda).
probabilidade(bia,portoalegre,terca).
probabilidade(bia,portoalegre,quarta).
probabilidade(bia,santamaria,quinta).
probabilidade(bia,apartamento,sexta).

%_____Adriano_____

probabilidade(adriano,apartamento,segunda).
probabilidade(adriano,apartamento,terca).
probabilidade(adriano,santamaria,quarta).
probabilidade(adriano,apartamento,quinta).
probabilidade(adriano,apartamento,sexta).

%_____Alice_____

probabilidade(alice,apartamento,segunda).
probabilidade(alice,portoalegre,terca).
probabilidade(alice,portoalegre,quarta).
probabilidade(alice,apartamento,quinta).
probabilidade(alice,apartamento,sexta).

%_____Bernardo_____
probabilidade(bernardo,santamaria,segunda).
probabilidade(bernardo,santamaria,terca).
probabilidade(bernardo,portoalegre,quarta).
probabilidade(bernardo,santamaria,quinta).
probabilidade(bernardo,apartamento,sexta).

%_____Maria_____

probabilidade(maria,apartamento,segunda).
probabilidade(maria,santamaria,terca).
probabilidade(maria,santamaria,quarta).
probabilidade(maria,santamaria,quinta).
probabilidade(maria,apartamento,sexta).

%_____________________________________________

%_____chave_____

chave(bia).

chave(X):-
	probabilidade(X,santamaria,segunda);
	probabilidade(X,portoalegre,terca).

%_____arma_____


roubo_arma(X):-  %definir_por_quem_a_arma_pode_ter_sido_roubada
	probabilidade(X,portoalegre,quinta);
	probabilidade(X,santamaria,quarta);
	probabilidade(X,apartamento,quarta);
	probabilidade(X,apartamento,quinta).

%_____acesso_____

acesso(X):- chave(X), roubo_arma(X).

%_____motivo_____
motivo(A):- 
	pobre(A);
	insano(A);
	ciume(A).

%_____assassino_____

assassino(X) :- motivo(X), acesso(X).