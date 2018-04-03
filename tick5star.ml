type color = int * int * int
type xy = int * int
datatype image = Image of xy * color array array

fun image ((x, y) : xy) c = Image((x, y), Array.tabulate(y, fn i => (Array.tabulate(x, fn j => c))));

fun size (Image(dim, a)) = dim;

fun drawPixel (Image(dim, arr)) c ((x, y) : xy) = Array.update(Array.sub(arr, y), x, c);



fun format4 i = StringCvt.padLeft #" " 4 (Int.toString i);

fun toPPM (Image(dim, arr)) filename =
  let val oc = TextIO.openOut filename
      val (w,h) = dim
  in
	TextIO.output(oc,"P3\n" ^ Int.toString w ^ " " ^ Int.toString h ^ "\n255\n");
	Array.app (fn x => (Array.app (fn (r, g, b) => (TextIO.output(oc, format4 r ^ format4 g ^ format4 b))) x; TextIO.output(oc, "\n"))) arr;
    TextIO.closeOut oc
  end;
  

fun drawHoriz _ _ _ 0 = ()
|	drawHoriz img c (x, y) d = (drawPixel img c (x, y); drawHoriz img c ((x + 1, y) : xy) (d - 1));

fun drawVert _ _ _ 0 = ()
|	drawVert img c (x, y) d = (drawPixel img c (x, y); drawVert img c ((x, y + 1) : xy) (d - 1));

fun drawDiag _ _ _ 0 = ()
|   drawDiag img c (x, y) d = (drawPixel img c (x, y); drawDiag img c ((x + 1, y + 1) : xy) (d - 1));


fun drawLine img c ((x0, y0) : xy) ((x1, y1) : xy) = 
  let 
	  val dx = Int.abs(x1 - x0)
	  val dy = Int.abs(y1 - y0)
	  val sx = Int.sign(x1 - x0)
	  val sy = Int.sign(y1 - y0)
	  fun line x y err = (drawPixel img c (x, y);
		if (x = x1 andalso y = y1) then () else
		if (2 * err > ~dy andalso 2 * err < dx) then line (x + sx) (y + sy) (err - dy + dx) else
		if (2 * err > ~dy) then line (x + sx) y (err - dy) else
		if (2 * err < dx) then line x (y + sy) (err + dx) else ()
	  )
  in
	line x0 y0 (dx - dy)
  end;

(*Star*)
  
fun drawAll func img = 
    let
        val Image((w, h), a) = img
        val i = ref 0
        val j = ref 0
    in
        while (!i < w) do (
            while (!j < h) do (
                drawPixel img (func((!i, !j):xy)) (!i, !j);
                j := !j + 1
            );
            i := !i + 1;
            j := 0
        )
    end;

fun gradient ((x,y):xy) : color =
    (((x div 30) * 30) mod 256, 0, ((y div 30) * 30) mod 256);
    
fun gradImage() = 
    let val img = image (640, 480) (0, 0, 0) in 
        drawAll gradient img;
        toPPM img "gradient.ppm"
    end;

    
fun mandelbrot maxIter (x, y) =
  let fun solve (a,b) c = 
      if c = maxIter then 1.0
      else
        if (a*a + b*b <= 4.0) then 
          solve (a*a - b*b + x,2.0*a*b + y) (c+1)
        else (real c)/(real maxIter)
  in
    solve (x,y) 0
  end;

fun chooseColour (n : real) : color =
  let
    val r = round ((Math.cos n) * 255.0)
    val g = round ((Math.cos n) * 255.0)
    val b = round ((Math.sin n) * 255.0)
  in
    (r, g, b)
  end;
  

fun rescale ((w, h) : xy) (cx, cy, s) ((x, y) : xy) = 
    let
        val p = s * (real x / real w - 0.5) + cx
        val q = s * (real y / real h - 0.5) + cy
    in
        (p, q)
    end;

fun compute dims (cx, cy, s) maxiter = 
    let
        val img = image dims (255, 255, 255)
    in
        drawAll (fn pos => chooseColour(mandelbrot maxiter (rescale dims (cx, cy, s) pos))) img;
        toPPM img "mandelbrot.ppm"
    end;
    
compute (640, 640) (0.0, 0.0,0.1) 32;