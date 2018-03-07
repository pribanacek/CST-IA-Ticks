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

fun change (till, chg, 0, fchg) = Cons(till, fchg)
	| change (till, [], amt, fchg) = fchg()		
	| change (till, c::chg, amt, fchg) =
		if amt < 0 then fchg()
		else change(c::till, c::chg, amt - c, fn() => change(till, chg, amt, fchg));