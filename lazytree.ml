datatype 'a seq = Nil
				| Cons of 'a * (unit -> 'a seq);
fun head (Cons(x,_)) = x;
fun tail (Cons(_,xf)) = xf();

fun from k = Cons(k, fn() => from(k + 1));

fun get(0, xq) = []
|   get(n, Nil) = []
|   get(n, Cons(x, xf)) = x :: get(n - 1, xf());

datatype 'a lazytree = Lf 
	| Br of 'a * (unit -> 'a lazytree) * (unit -> 'a lazytree);


fun join(t, Lf) = t
|   join(Lf, t) = t
|   join(Br(v, f1, f2), Br(u, f3, f4)) = 
		if u < v then Br(v, fn() => join(f1(), Br(u, f3, f4)), f2)
		else Br(v, f1, fn() => join(f2(), Br(u, f3, f4)));

fun labels(Br(v, f1, f2)) = Cons(v, fn() => labels(join(f1(), f2())))
|	labels(Lf) = Nil;