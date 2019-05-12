defmodule Lista1 do

  def sumSquares(a,b) do  a * a + b * b end

  def hasEqHeads(lis1,lis2) do
     [h|t] = lis1
     [h2|t2] = lis2
     h === h2
  end

  def addSuper(name)do
       "Super" <> name
  end

  def foundSpaces (string1)do
     length String.split(string1," ")
  end

  def aplyExpr(list) do
    Enum.map(list,fn(n)-> 3 * (n *n) + (2 / n) + 1 end)
  end

  def negatives(list)do
    Enum.filter(list, fn(n)-> n < 0 end )
  end

  def btw1n100(list) do
    Enum.filter(list, fn(n)-> 1 <= n and n <= 100 end)
  end

  def idade(list) do
    Enum.filter(list, fn(n)-> (2019 - n) > 1980 end)
  end

  def par(list) do
    Enum.filter(list, fn(n)-> rem(n,2) === 0 end)
  end

  def contChar(c,str) do
    String.contains?str,c
  end

  def onlyTermA(str) do
    Enum.filter(str,fn(n)-> String.ends_with?n,"a" end)
  end

end
