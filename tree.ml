datatype 'a tree = Lf
				| Br of 'a * 'a tree * 'a tree;
				
fun sumtree(Lf) = 0
|   sumtree(Br(x, t1, t2)) = x + sumtree(t1) + sumtree(t2);

fun ftree(k, n) = if n = 0 then Lf else Br(k, ftree(2 * k, n - 1), ftree(2 * k + 1, n - 1));
