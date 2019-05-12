# Atividade Personalizada em Python
# Lista 1 de programação Funcional feita em python.

def sumSquares(a,b):
    return (a*a) + (b*b)

def hasEqHeads(list1,list2):
    return list1[0] == list2[0]

def addSuper(s):
    return "Super"+s

def countSpaces(s):
    return len(list(filter(lambda c: c == ' ',s)))

def applyExpression(lista):
    return list(map(lambda x: (3*(x*x)) + (2/x + 1),lista))

def negativos(lista):
    return list(filter(lambda x: x < 0, lista))

def btw1n100(lista):
    return list(filter(lambda x: (x <= 100) and (x >= 1), lista))

def verifIdade(lista):
    return list(filter(lambda x: (2019-x)>1980,lista))

def pares(lista):
    return list(filter(lambda x: x % 2 == 0,lista))

def verifChar(s,ch):
    return len(list(filter(lambda c: c == ch,s)))>0

def onlyTermA(s):
    return list(filter(lambda c: c[-1]=='a',s))

