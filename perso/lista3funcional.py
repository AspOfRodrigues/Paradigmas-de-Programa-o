# Atividade Personalizada em Python
# Lista 3 de programação Funcional feita em python.

def add10toall (n):
    return [x + 10 for x in n]

def multN (n,lista):
    return [x * n for x in lista]

def applyExpr(lista):
    return [3*x+2 for x in lista]

def addSuffix(s,lista):
    return [ st + s for st in lista]

def selectGet5(lista):
    return [ x for x in lista if x > 5]

def sumOdds(lista):
    return sum([x for x in lista if (x % 2 != 0)])

def selectExpr(lista):
    return [x for x in lista if (x >=20) if (x<=50)]

def countShorts(lista):
    return [x for x in lista if len(x)<5]

def calcExpr(lista):
    return [(x*x)/2 for x in lista if((x*x)/2)>10]

def trSpaces(lista):
    s = (lista.replace(" ","-"))
    return print(s)

def selectSnd(lista):
    return [y[1] for y in lista ]

def dotProd(lista1,lista2):
    lista = zip(lista1,lista2)
    return [x[0]*x[1] for x in lista]
