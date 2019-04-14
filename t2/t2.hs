import Text.Printf

type Point     = (Float,Float)
type Rect      = (Point,Float,Float)
type Circle    = (Point,Float)


-------------------------------------------------------------------------------
-- Paletas
-------------------------------------------------------------------------------

rgbPalette :: Int -> [(Int,Int,Int)]
rgbPalette n = take n $ cycle [(255,0,0),(0,255,0),(0,0,255)]

greenPalette :: Int -> [(Int,Int,Int)]
greenPalette n = [(0,80+i*10,0) | i <- [0..n] ]

redPalette :: Int -> [(Int,Int,Int)]
redPalette n = [(80+i*10,0,0) | i <- [0..n] ]

bluePalette :: Int -> [(Int,Int,Int)]
bluePalette n = [(0,0,80+i*10) | i <- [0..n] ]


-------------------------------------------------------------------------------
-- Geração de retângulos em suas posições
-------------------------------------------------------------------------------

genRectsInLine :: Int -> Int -> [Rect] --
genRectsInLine n c = [((m*(w+gap), z*(h+gap)  ),w,h) | m <- [0..fromIntegral(n-1)] , z<-[0..fromIntegral(c-1)]] 
  where (w,h) = (50,50)
        gap = 10

-------------------------------------------------------------------------------
-- Geração de circulos 
-------------------------------------------------------------------------------
-- Case 2 ---------------------------------------------------------------------
genCircInCircles ::  Float -> Int -> [Circle]
genCircInCircles r n  = [((posx, posy), 2*(r/5))| qtd <- [0..n] , posx <- [(r + gap) + ((r+(gap/2)) * sin( fromIntegral(qtd) * (2 * pi/(fromIntegral n))))] , posy <-[(r + gap) + ((r+(gap/2)) * cos( fromIntegral(qtd) * (2 * pi/(fromIntegral n))))] ] -- 
  where r = 50
        gap = 100 
        --posCircleX = (r + gap) + (r * sin( fromIntegral(qtd) * (2 * pi/(fromIntegral t))))
        --posCircleY = (r + gap) + (r * cos( fromIntegral(qtd) * (2 * pi/(fromIntegral t))))
------------------------------------------------------------------------------
-- Case 3 ---------------------------------------------------------------------
-- Geracao de circulos sobrepostos
------------------------------------------------------------------------------
genCircSobCircles ::  Float -> Int -> [Circle]
genCircSobCircles r n  = [((posx, posy), r + 2*(r/3))| qtd <- [0..n] , posx <- [(r + gap) + (r * sin( fromIntegral(qtd) * (2 * pi/(fromIntegral n))))] , posy <-[(r + gap) + (r * cos( fromIntegral(qtd) * (2 * pi/(fromIntegral n))))] ] -- 
  where r = 25
        gap = 100
        --posCircleX = (r + gap) + (r * sin( fromIntegral(qtd) * (2 * pi/(fromIntegral t))))
        --posCircleY = (r + gap) + (r * cos( fromIntegral(qtd) * (2 * pi/(fromIntegral t))))
genCircSobCirclesInLine :: Float -> Int -> [Circle]
genCircSobCirclesInLine r n  = [((posx , posy), r + 2*(r/3) )| qtd <- [0..n] , posx <- [(r + gap) + (r * sin( fromIntegral(qtd) * (2 * pi/(fromIntegral n))))] , posy <-[(r + gap) + (r * cos( fromIntegral(qtd) * (2 * pi/(fromIntegral n))))] ] -- 
  where r = 50
        gap = 50
--genCircSobCirclesInLine n r control= [((genCircSobCirclesInLine r control )) | m <- [0..fromIntegral(n-1)]]

------------------------------------------------------------------------------
-- Case 4 ---------------------------------------------------------------------
-- Geracao de circulos em curvas
------------------------------------------------------------------------------
genCircInCurves ::  Float -> Int -> [Circle]
genCircInCurves r n  = [((posx, posy), r - 2*(r/3))| qtd <- [0..n] , posx <- [(r + gap) + ((r+gap) * ( fromIntegral(qtd) * (2 * pi/(fromIntegral n))))] , posy <-[(r + gap*4) + ((r+gap) * sin(fromIntegral(qtd) * (2 * pi/(fromIntegral n))))] ] -- r * cos
  where r = 20
        gap = 15
-------------------------------------------------------------------------------
-- Strings SVG
-------------------------------------------------------------------------------

-- Gera string representando retângulo SVG 
-- dadas coordenadas e dimensoes do retângulo e uma string com atributos de estilo

svgRect :: Rect -> String -> String 
svgRect ((x,y),w,h) style = 
  printf "<rect x='%.3f' y='%.3f' width='%.2f' height='%.2f' style='%s' />\n" x y w h style

--Gera string representando circulo em SVG
--Dado o raio e as coord da cicunferencia e uma string com atributos de estilo

svgCircle :: Circle -> String -> String
svgCircle ((cordx,cordy),raio) style =
  printf "<circle cx='%.3f' cy='%.3f' r='%.2f' style='%s' />\n" cordx cordy raio style


-- String inicial do SVG
svgBegin :: Float -> Float -> String
svgBegin w h = printf "<svg width='%.2f' height='%.2f' xmlns='http://www.w3.org/2000/svg'>\n" w h 

-- String final do SVG
svgEnd :: String
svgEnd = "</svg>"

-- Gera string com atributos de estilo para uma dada cor
-- Atributo mix-blend-mode permite misturar cores
svgStyle :: (Int,Int,Int) -> String
svgStyle (r,g,b) = printf "fill:rgb(%d,%d,%d); mix-blend-mode: screen;" r g b

-- Gera strings SVG para uma dada lista de figuras e seus atributos de estilo
-- Recebe uma funcao geradora de strings SVG, uma lista de círculos/retângulos e strings de estilo
svgElements :: (a -> String -> String) -> [a] -> [String] -> String
svgElements func elements styles = concat $ zipWith func elements styles

-------------------------------------------------------------------------------
-- Função principal que gera arquivo com imagem SVG
-------------------------------------------------------------------------------

main :: IO ()
main = do
  writeFile "caseX.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgRect rects (map svgStyle palette)
        rects = genRectsInLine nrects crects
        palette = greenPalette nrects
        nrects = 44
        crects = 5
        (w,h) = (1500,500) -- width,height da imagem SVG

----------------------------T2-------------------------------------------------
--------------------------Case1------------------------------------------------
genCase1 :: IO ()
genCase1 = do
  writeFile "case1.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgRect rects (map svgStyle palette)
        rects = genRectsInLine nrects crects
        palette = bluePalette nrects
        nrects = 44
        crects = 5
        (w,h) = (1500,500) -- width,height da imagem SVG
--------------------------Case2------------------------------------------------
genCase2 :: IO ()
genCase2 = do
  writeFile "case2.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgCircle circle (map svgStyle palette)
        --rects = genRectsInLine nrects crects
        circle = genCircInCircles raio ncircles
        palette = rgbPalette ncircles
        ncircles = 12
        raio = 50
        --nrects = 44
        --crects = 5
        (w,h) = (1500,500) -- width,height da imagem SVG

--------------------------Case3------------------------------------------------
genCase3 :: IO ()
genCase3 = do
  writeFile "case3.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgCircle circle (map svgStyle palette)
        circle = genCircSobCircles raio ncircles
        palette = rgbPalette ncircles
        ncircles = 3
        raio = 50
        (w,h) = (1500,500) -- width,height da imagem SVG
--------------------------Case4------------------------------------------------
genCase4 :: IO ()
genCase4 = do
  writeFile "case4.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgCircle circle (map svgStyle palette)
        circle = genCircInCurves raio ncircles
        palette = redPalette ncircles
        ncircles = 15
        raio = 15
        (w,h) = (1500,500) -- width,height da imagem S


---------------------Notas(apagar antes de enviar)------------------------------
--variavel gap : uso para definir o espaco entre as figuras e as bordas da img
--a parte dos circulos estar hard coded, tentar mudar antes do envio
--tentar implementar uma color wheel
--Gerar novos testes do GHci que foram perdidos quando o notebook se desligou 
