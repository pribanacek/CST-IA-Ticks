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
	  val i = ref 0
	  val j = ref 0
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