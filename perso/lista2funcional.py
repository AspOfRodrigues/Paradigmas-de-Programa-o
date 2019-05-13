# Atividade Personalizada em Python
# Lista 2 de programação Funcional feita em python.

def isVowell(c):
    s = "aeiouAEIOU"
    return c in s

def addComma(lista):
    return [ s+',' for s in lista]

def addHtml(lista):
    return [ '<LI>'+ s +'<li>' for s in lista]

def semVogais(lista):
    s = "aeiouAEIOU"
    return list(filter(lambda c: not c in s,lista))

def codifica(lista):
    s = (lista.replace("a" , "-"))
    s = (s.replace("b","-"))
    s = (s.replace("c", "-"))
    s = (s.replace("d", "-"))
    s = (s.replace("e", "-"))
    s = (s.replace("f", "-"))
    s = (s.replace("g", "-"))
    s = (s.replace("h", "-"))
    s = (s.replace("i", "-"))
    s = (s.replace("j", "-"))
    s = (s.replace("k", "-"))
    s = (s.replace("l", "-"))
    s = (s.replace("m", "-"))
    s = (s.replace("n", "-"))
    s = (s.replace("o", "-"))
    s = (s.replace("p", "-"))
    s = (s.replace("q", "-"))
    s = (s.replace("r", "-"))
    s = (s.replace("s", "-"))
    s = (s.replace("t", "-"))
    s = (s.replace("u", "-"))
    s = (s.replace("v", "-"))
    s = (s.replace("x", "-"))
    s = (s.replace("y", "-"))
    s = (s.replace("w", "-"))
    s = (s.replace("z", "-"))
    return print(s)

def first_name(lista):
    s = lista.split(" ")
    return s[0]

def isInt (lista):
    return not(lista.isalpha())

def reverse_slicing(s):
    return s[::-1]

def last_name(lista):
    return reverse_slicing(first_name(reverse_slicing(lista)))

def user_name(lista):
    return lista[0] + last_name(lista)

def encode_name(lista):
    s = (lista.replace("a","4"))
    s = (s.replace("e", "3"))
    s = (s.replace("i", "2"))
    s = (s.replace("o", "1"))
    s = (s.replace("u", "0"))
    return print(s)

def better_encode_name(lista):
        s = (lista.replace("a", "4"))
        s = (s.replace("e", "3"))
        s = (s.replace("i", "1"))
        s = (s.replace("o", "0"))
        s = (s.replace("u", "00"))
        return print(s)