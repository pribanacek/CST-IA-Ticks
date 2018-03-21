datatype 'a tree = Lf
				| Br of 'a * 'a tree * 'a tree;

fun tcons v Lf = Br(v, Lf, Lf)
|   tcons v (Br(w, t1, t2)) = Br(v, tcons w t2, t1);

fun arrayoflist([]) = Lf
|	arrayoflist(x::xs) = tcons x (arrayoflist(xs));


fun join(t, Lf) = t
|	join(Lf, t) = t
|	join(Br(x, t1, t2), t3) = Br(x, t3, join(t1, t2));


fun listofarray(Lf) = []
|   listofarray(Br(x, t1, t2)) = x :: listofarray(join(t1, t2));

fun getEvens([], i) = []
|	getEvens(x::xs, i) = if x mod 2 = 0 then 
							i :: getEvens(xs, i + 1) 
							else getEvens(xs, i + 1);

fun getSubsOfEvens(t) = getEvens(listofarray(t), 1);
