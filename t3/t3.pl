
:- use_module(library(random)).

%exercicio1

odd(N) :- A is N mod 2, A = 1.

%exercicio2

hasN([],0).
hasN([H|T],N) :- I is N-1, hasN(T,I).

%exercicio3

inc99([],[]).
inc99([H1|T1],[H2|T2]) :- 
 X is H1 + 99,
 H2 = X,
 inc99(T1,T2).

%exercicio4

incN([],[],_).
incN([H1|T1],[H2|T2],N) :- 
 X is H1 + N,
 H2 = X,
 incN(T1,T2,N).

%exercicio5

comment([],[]).
comment([H1|T1],[H2|T2]) :- 
 string_concat("%%",H1, H2), comment(T1,T2).

%exercicio6

par(N) :- A is N mod 2, A = 0.

onlyEven([],[]).
onlyEven([H1|T1],[H2|T2]) :- par(H1), H2 is H1, onlyEven(T1,T2); odd(H1),onlyEven(T1,[H2|T2]).

%exercicio7

countdown(0,[]).
countdown(N,[H|T]) :- H = N, X is N - 1, countdown(X,T).

%exercicio8

nRandoms(0,[]).
nRandoms(N,[H|T]) :- random_between(0, 100, X), H is X, A is N - 1, nRandoms(A,T).

%exercicio9

potN0(0,[1]).
potN0(N,[H|T]) :- H is 2^N, X is N - 1, potN0(X,T).

%exercicio10

zipMult([],[],[]).
zipMult([H1|T1],[H2|T2],[H3|T3]) :- H3 is H1 * H2, zipMult(T1,T2,T3).

%exercicio11

potencias(0,[]).
potencias(N,[H|T]) :- potN0(N,X) , reverse([H|T],X).

%exercicio12

cedulas(0,[],[]).
cedulas(N,[H1|T1],[H2|T2]):- H2 is div(N,H1), X is N - (H1*H2), cedulas(X,T1,T2).