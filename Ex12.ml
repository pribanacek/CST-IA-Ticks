fun power(n, p) =
	let val nr = ref n
	    val pr = ref p in
	while !pr > 1 do
		(nr := n * !nr;  pr := !pr - 1);
	!nr end;
	
fun t(r) =
	let val a = ref r 
		val b = ref r in
	while (b := 10 * !b; !a < 3 * r) do
		(a := !a + 1);
	(!a, !b)
	end;
	
fun swap(xr, yr) = let val x = !xr in xr := !yr; yr := x end;

fun idMatrix(n) = 
    let val a = Array.tabulate(n, fn _ => (Array.tabulate(n, fn _ => 0)))
	    val i = ref 0 in
	while !i < n do
		(Array.update(Array.sub(a, !i), !i, 1);
		i := !i + 1);
	a end;
	
fun trans(a) = 
		let val m = ref (Array.length(a))
			val n = ref (Array.length(Array.sub(a, 0)))
			val ar = Array.tabulate(!n, fn _ => (Array.tabulate(!m, fn _ => 0))) in
		while !m > 0 do (
			n := Array.length(Array.sub(a, 0));
			while !n > 0 do (
				Array.update(Array.sub(ar, !n - 1), !m - 1, Array.sub(Array.sub(a, !m - 1), !n - 1));
				n := !n - 1
			);
			m := !m - 1
		);
		ar end;