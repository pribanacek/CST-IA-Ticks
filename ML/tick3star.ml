datatype 'a tree = Lf
				| Br of 'a * 'a tree * 'a tree;
				
			
fun insert(x : string, Lf) = Br(x, Lf, Lf)
|	insert(x, Br(v, t1, t2)) =
		if x < v then Br(v, insert(x, t1), t2)
		else Br(v, t1, insert(x, t2));

fun member(x : string, Lf) = false
|	member(x, Br(v, t1, t2)) =
		if x < v then member(x, t1)
		else if x > v then member(x, t2)
		else true;

fun union(t, Lf) = t
|   union(Lf, t) = t
|   union(Br(x : string, t1, t2), Br(y, t3, t4)) = 
        if x > y then Br(x, union(t1, Br(y, t3, t4)), t2)
		else if x < y then Br(x, t1, union(t2, Br(y, t3, t4)))
		else Br(x, union(t1, t3), union(t2, t4));