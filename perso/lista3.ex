defmodule Lista3 do

  def add10toall(list) do
    for x <- list, do: x + 10
  end

  def multN(n,list) do
    for x <- list, do: x * n
  end

  def applyExpr(list) do
    for x <- list, do: 3*x+2
  end

  def addSuffix(list) do
    for str <- list, do: str <> "@inf.ufsm.br"
  end

  def selectGet5(list) do
    for n <-list, n > 5 , do: n + 0
  end

  def sumOdds(list) do
    for(n <- list, rem(n,2) === 1, do: n + 0)|> Enum.sum()
  end

  def selectExpr(list) do
    for n <- list, rem(n,2) === 0, n >= 20, n <=50, do: n + 0
  end

  def countShorts(list) do
    for(str <- list, String.length(str) < 5, do: str <> "") |> length()
  end

  def calcExpr(list) do
    for x <- list,(x*x/2) > 10 , do: x*x/2
  end

  def trSpaces(list) do
    for str <- list, do: String.replace(str, " ", "-")
  end

  def selectSnd(list) do
    for {_,snd} <- list, do: snd
  end

  def dotProd(listA,listB) do
    list = Enum.zip(listA,listB)
    for({a,b} <- list, do: a*b) |> Enum.sum
  end
end
