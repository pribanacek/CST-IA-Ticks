datatype 'a seq = Nil
				| Cons of 'a * (unit -> 'a seq);
fun head (Cons(x,_)) = x;
fun tail (Cons(_,xf)) = xf();

fun from k = Cons(k, fn() => from(k + 1));

fun get(0, xq) = []
|   get(n, Nil) = []
|   get(n, Cons(x, xf)) = x :: get(n - 1, xf());


fun mapseq f Nil = Nil
|   mapseq f (Cons(x, xf)) = Cons(f x, fn() => mapseq f (xf()));



(*this bit*)


fun getBinary(0, l) = l
|   getBinary(x, l) = getBinary(x div 2, (x mod 2) :: l);

fun bin(n) = Cons(getBinary(n, []), fn() => bin(n + 1));


fun rev ([], ys) = ys
|   rev (x::xs, ys) = rev(xs, x::ys);

fun getPal(0) = []
|   getPal(x) =
		let val b = getBinary(x, []) in
			if b = rev(b, []) then b
			else getPal(x + 1)
			end;
			
fun pal(n) = Cons(getPal(n), fn() => pal(n + 1));
