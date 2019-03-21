--Exercicios da primeira aula pratica--

--Exercicio 1 -- 
sumSquares :: Int -> Int -> Int
sumSquares  x y = (x^2 + y^2)

--Exercicio 2 -- 
hasEqHeads :: [Int] -> [Int] -> Bool
hasEqHeads lis list = if (head lis) == (head list) then True else False

--Exercicio 3 -- 

addSuper :: String -> String 
addSuper str = "Super" ++ str

--Exercicio 4 --

retornaValorEsp :: String -> String
retornaValorEsp str = filter (\ c -> c ==' ') str

retornaResul :: String -> Int
retornaResul str = length(retornaValorEsp(str))

--Exercicio 5 -- 
eqApli :: [Int] -> [Int] 
eqApli    n = map (\ n -> (3 * n ^ 2 + 2) `div` (n + 1))n

--Exercicio 6 --
retNegativo :: [Int] -> [Int] 
retNegativo n = filter (\element -> element < 0) n

-- Exercicio 7-- 
retNum :: [Int] -> [Int]
retNum  n = filter (\n -> (n>1) && (n<100)) n

-- Exercicio 8 -- 
retIdade :: [Int] -> [Int]
retIdade n = filter (\n -> (2019 - n) > 1980 ) n

-- Exercicio 9 -- 
retPar :: [Int] -> [Int]
retPar n = filter (\n -> mod n 2 == 0) n 

-- Exercicio 10 -- 
charFound :: Char -> String -> Bool
charFound ch str = length (filter (== ch) str) > 0
-- Exercicio 11 --
retornaNome :: [String] -> [String]
retornaNome n = filter (\ element -> (last element) == 'a') n 
-- Exercicio 12 -- 

--takeWhile (>0 ) [2, 4, 5, -1, -3, 6, 10]