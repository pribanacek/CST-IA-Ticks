datatype 'a tree = Lf
				| Br of 'a * 'a tree * 'a tree;
				
exception Subscript;

fun sub(Lf, _) = raise Subscript
|	sub(Br(v, t1, t2), k) =
		if k = 1 then v
		else if k mod 2 = 0 then sub(t1, k div 2) else sub(t2, k div 2);
		
fun update(Lf, k, w) = 
		if k = 1 then Br(w, Lf, Lf)
		else raise Subscript
|	update(Br(v, t1, t2), k, w) = 
		if k = 1 then Br(w, t1, t2)
		else if k mod 2 = 0
				then Br(v, update(t1, k div 2, w), t2)
				else Br(v, t1, update(t2, k div 2, w));
				
(*fun delFirst(Br(v, t1, t2)) = *)