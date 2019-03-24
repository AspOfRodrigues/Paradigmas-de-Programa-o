-- ALUNO: HENRIQUE RODRIGUES DA SILVA--

-- PARADIGMAS DE PROGRAMACAO : TRAB 1 --

import Data.Char

-- FUNCAO 1 -- 

isVowel :: Char -> Bool
isVowel c = if ( c == 'A' || c == 'a' || c == 'E' || c == 'e' || c == 'I' || c == 'i' || c == 'O' || c == 'o' || c == 'U' || c == 'u') then True else False

-- FUNCAO 2 --

addComma :: [String] -> [String]
addComma str =  map (\ c -> c ++ ",") str

-- FUNCAO 3 --

-- COM FUNCOES ANONIMAS --

addHtml :: [String] -> [String]
addHtml str = map (\ c -> "<LI>" ++ c ++ "<LI>") str 

-- SEM FUNCOES ANONIMAS -- 

addHtmlan :: String -> String
addHtmlan strg = ("<LI>" ++ strg ++ "<LI>")
addHtmlfunc ::  [String] -> [String]
addHtmlfunc str = map (addHtmlan) str

-- FUNCAO 4 --

-- COM FUNCOES ANONIMAS -- 

rmvVowel :: String -> String
rmvVowel str = filter( \str -> notElem str "aeiouAEIOU" ) str
remVog :: String -> String
remVog str = filter(`notElem` "aeiouAEIOU") str

-- FUNCAO 5 -- 

-- COM FUNCOES ANONIMAS --

substTrac :: String -> String
substTrac str = map(\ n -> if n /= ' ' then '-' else n ) str

-- SEM FUNCOES ANONIMAS -- 

subsTc :: Char -> Char
subsTc ch = if ch/= ' ' then '-' else ch
encondeS :: String -> String 
encondeS str = map(subsTc) str

-- FUNCAO 6 -- 

firstName :: String -> String
firstName str = takeWhile (/= ' ') str

-- FUNCAO 7 -- 

isInt :: String -> Bool
isInt str = if length str > length (filter(`elem` "0123456789") str) then False else True

-- FUNCAO 8 --

lastName :: String -> String
lastName str = reverse(firstName (reverse str))

-- FUNCAO 9 -- 

userName :: String -> String
userName str = map toLower (concat [take 1 (head (words str)), lastName str ])

-- FUNCAO 10 --

substVowel :: Char -> Char
substVowel  c 
             | c == 'a' = '4'
             | c == 'A' = '4'
             | c == 'e' = '3'
             | c == 'E' = '3'
             | c == 'i' = '2'
             | c == 'I' = '2'             
             | c == 'o' = '1'
             | c == 'O' = '1'
             | c == 'u' = '0'
             | c == 'U' = '0'
             | otherwise = c
encodeName :: String -> String
encodeName str = map (substVowel) str

-- FUNCAO 11 --

betterSubstVowel :: Char -> String
betterSubstVowel c 
             | c == 'a' = "4"
             | c == 'A' = "4"
             | c == 'e' = "3"
             | c == 'E' = "3"
             | c == 'i' = "1"
             | c == 'I' = "1"
             | c == 'o' = "0"
             | c == 'O' = "0"
             | c == 'u' = "00"
             | c == 'U' = "00"
             | otherwise = [c]
betterEncodeName :: String -> String
betterEncodeName str = (concatMap (\ch -> betterSubstVowel ch) str)

-- FUNCAO 12 -- 
verifDez :: [String] -> [String]
verifDez str = map (\c -> if length c >= 10 then take 10 c else take 10 (c ++ ".........") ) str