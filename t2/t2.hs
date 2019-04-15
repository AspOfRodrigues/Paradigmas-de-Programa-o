import Text.Printf

type Point     = (Float,Float)
type Rect      = (Point,Float,Float)
type Circle    = (Point,Float)

------------------------------------------------------------------------------
------------------------------------------------------------------------------
------------------------------------------------------------------------------
-- Paletas
------------------------------------------------------------------------------
------------------------------------------------------------------------------
------------------------------------------------------------------------------

rgbPalette :: Int -> [(Int,Int,Int)]
rgbPalette n = take n $ cycle [(255,0,0),(0,255,0),(0,0,255)]

greenPalette :: Int -> [(Int,Int,Int)]
greenPalette n = [(0,80+i*5,0) | i <- [0..n] ]

redPalette :: Int -> [(Int,Int,Int)]
redPalette n = [(80+i*5,0,0) | i <- [0..n] ]

bluePalette :: Int -> [(Int,Int,Int)]
bluePalette n = [(0,0,80+i*5) | i <- [0..n] ]

------------------------------------------------------------------------------
------------------------------------------------------------------------------
------------------------------------------------------------------------------
------------------------------------------------------------------------------
-- Geração de retângulos em suas posições ------------------------------------
------------------------------------------------------------------------------
-- Case 1 --------------------------------------------------------------------
genRectsInLine :: Int -> Int -> [Rect] --
genRectsInLine n c = [((m*(xSpace), z*(ySpace)),w,h) | m <- [0..fromIntegral(n-1)] , z<-[0..fromIntegral(c-1)]] 
  where (w,h) = (80,50)
        gap = 5
        xSpace = w + gap
        ySpace = h + gap

------------------------------------------------------------------------------
------------------------------------------------------------------------------
------------------------------------------------------------------------------
-- Geração de circulos -------------------------------------------------------
------------------------------------------------------------------------------
-- Case 2 --------------------------------------------------------------------
posCircleX :: Float -> Int -> Int -> Float
posCircleX r n qtd = (space)+(spcCirc*sin(fromIntegral(qtd)*(cst)))
  where constante = 2 * pi/(fromIntegral n)
        gap = 150
        space = r + gap
        spcCirc = r + (gap/2) -- Space Between Circles
        cst = 2 * pi/(fromIntegral n) -- constante

posCircleY :: Float -> Int -> Int -> Float
posCircleY r n qtd = (space)+(spcCirc*cos(fromIntegral(qtd)*(cst)))
  where constante = 2 * pi/(fromIntegral n)
        gap = 150
        space = r + gap
        spcCirc = r + (gap/2) -- Space Between Circles
        cst = 2 * pi/(fromIntegral n) -- constante

genCircInCircles :: Float -> Int -> [Circle]
genCircInCircles r n = [((posx, posy),2*(r/5))| qtd <- [0..n],posx <- [posCircleX r n qtd],posy <- [posCircleY r n qtd]] -- 
  where r = 50
        gap = 150
------------------------------------------------------------------------------
------------------------------------------------------------------------------
------------------------------------------------------------------------------
-- Geracao de circulos sobrepostos -------------------------------------------
------------------------------------------------------------------------------
-- Case 3 --------------------------------------------------------------------
geraPos :: Float -> Float -> Int -> Int -> Int -> [Point]
geraPos w h columns lines gap = [(((circleW / const) * fromIntegral y), ((circleH / const) * fromIntegral x )) | x <- [1..lines], y <- [1..columns]]
  where const = 2 + fromIntegral gap
        circleW = w / fromIntegral columns 
        circleH = h / fromIntegral lines 

genTripleCirc ::  Point -> Float -> Int -> [Circle]
genTripleCirc (x,y) r n = [( (x + (cos i * 36),y - (sin i * 36)) ,r) | i <- [0,s..pi]]
  where s = pi/ fromIntegral (n - 1) 

------------------------------------------------------------------------------
------------------------------------------------------------------------------
------------------------------------------------------------------------------
-- Case 4 --------------------------------------------------------------------
------------------------------------------------------------------------------
-- Geracao de circulos em curvas ---------------------------------------------
genCircInCurves ::  Float -> Int -> [Circle]
genCircInCurves r n  = [((posx, posy), r - 2*(r/3))| qtd <- [0..n] , posx <- [posCircleXCurve r n qtd] , posy <-[posCircleYCurve r n qtd] ] 
  where r = 20
        gap = 15

posCircleXCurve :: Float -> Int -> Int -> Float
posCircleXCurve r n qtd = (r + gap) + ((r+gap) * ( fromIntegral(qtd) * (2 * pi/(fromIntegral n))))
  where constante = 2 * pi/(fromIntegral n)
        gap = 30

posCircleYCurve :: Float -> Int -> Int -> Float
posCircleYCurve r n qtd = (r + gap*4) + ((r+gap) * sin(fromIntegral(qtd) * (2 * pi/(fromIntegral n))))
  where constante = 2 * pi/(fromIntegral n)
        gap = 30
        
-------------------------------------------------------------------------------
------------------------------------------------------------------------------
------------------------------------------------------------------------------
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

--main :: IO ()
--main = do
  --writeFile "caseX.svg" $ svgstrs
  --where svgstrs = svgBegin w h ++ svgfigs ++ svgEnd
        --svgfigs = svgElements svgRect rects (map svgStyle palette)
        --rects = genRectsInLine nrects
        --palette = greenPalette nrects
        --nrects = 10
        --(w,h) = (1500,500) -- width,height da imagem SVG

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
        circle = genCircInCircles raio ncircles
        palette = rgbPalette ncircles
        ncircles = 24
        raio = 50
        (w,h) = (1500,500) -- width,height da imagem SVG

--------------------------Case3------------------------------------------------
genCase3 :: IO ()
genCase3 = do
  writeFile "case3.svg" $ svgstrs
  where svgstrs = svgBegin w h ++ svgfigs ++ svgEnd
        svgfigs = svgElements svgCircle circle (map svgStyle palette)
        circle = concat [genTripleCirc (x,y) 50 3 | (x,y) <- geraPos 25000 20000 4 3 30]
        palette = rgbPalette ncircles
        ncircles = 36
        raio = 50
        (w,h) = (1500,1500) -- width,height da imagem SVG
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