defmodule Lista2 do
  def isVowell(c) do
    case c do
      'a' -> true
      'e' -> true
      'i' -> true
      'o' -> true
      'u' -> true
      _ -> false end
  end

  def addComma(str) do
    Enum.map(str, fn(x)-> x <> "," end)
  end

  def addHtml(str) do
    Enum.map(str, fn(x)-> "<LI>" <> x <> "<LI>" end)
  end

  def insertHtml(str) do
    "<LI>" <> str <> "<LI>"
  end

  def addHtmltolist(str) do
    Enum.map(str,&insertHtml/1)
  end

  def semVogais1(str) do
    String.replace(str, ~r/[aeiou]/, "")
  end

  def semVogais2(str) do
    Enum.filter(str, fn(n)-> n !== ~r/[aeiou]/ end )
  end

  def codifica(str) do
    String.replace(str, ~r/[abcdefghijklmnopqrstuvxwyzABCDEFGHIJKLMNOPQRSTUVXWYZ]/, "-")
  end

  def first_name(str) do
    String.to_charlist(str) |> Enum.take_while(fn(n) -> n != ?\s end)
  end

  def verifInt(str) do
  not Regex.match?(~r/[^01234567898]/, str)
  end

  def last_name(str) do
    String.split(str," ") |> Enum.reverse |> hd
  end

  def user_name(str) do
    String.at(str,0)   <> last_name(str) |> String.downcase();
  end

  def encode_name(str) do
    String.replace(str, ~r/[a]/,"4") |> String.replace( ~r/[e]/,"3") |> String.replace( ~r/[i]/,"2") |> String.replace( ~r/[o]/,"1") |> String.replace( ~r/[u]/,"0")

  end

  def better_encode_name(str) do
    String.replace(str, ~r/[a]/,"4") |> String.replace( ~r/[e]/,"3")|> String.replace( ~r/[i]/,"1") |> String.replace( ~r/[o]/,"0") |> String.replace( ~r/[u]/,"00")
  end

  def take10(str) do
    Enum.map(str, fn(x) -> if String.length(x) > 10 do String.slice(x,0,10) else x <> ".........." |> String.slice(0,10)end end)
  end

end
