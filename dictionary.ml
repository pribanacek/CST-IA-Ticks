datatype 'a tree = Lf
				| Br of 'a * 'a tree * 'a tree;
				
exception Missing of string;

fun lookup(Br((a, x), t1, t2), b) = 
		if	b < a then lookup(t1, b)
		else if a < b then lookup(t2, b)
		else x
|	lookup(Lf, b) = raise Missing b;

fun update(Lf, b:string, y) = Br((b, y), Lf, Lf)
|	update(Br((a, x), t1, t2), b, y) = 
		if b < a then Br((a, x), update(t1, b, y), t2)
		else if a < b then Br((a, x), t1, update(t2, b, y))
		else (*a=b*) Br((a, y), t1, t2);


		
		
exception Collision of string * int;

fun insert(Lf, (b:string, y)) = Br((b, y), Lf, Lf)
|   insert(Br((a, x), t1, t2), (b, y)) = 
		if b < a then Br((a, x), insert(t1, (b, y)), t2)
		else if a < b then Br((a, x), t1, insert(t2, (b, y)))
		else (*a=b*) raise Collision (a, x);

		
(*traversing the tree*)		
fun preord(Lf, vs) = vs
|	preord(Br(v, t1, t2), vs) = v :: preord(t1, preord(t2, vs));

fun mergeTrees(t1, t2) = preord(t1, []) @ preord(t2, []);

fun insertList([], t) = t
|	insertList(x::xs, t) = insertList(xs, insert(t, x));

(*deleting the node, along with its subtrees*)
fun deleteWM(Lf, b) = raise Missing b
|	deleteWM(Br((a, x), t1, t2), b) = 
		if b < a then Br((a, x), deleteWM(t1, b), t2)
		else if a < b then Br((a, x), t1, deleteWM(t2, b))
		else (*a=b*) Lf;
		
(*gets the subtrees that were deleted*)
fun getSubs(Lf, b) = raise Missing b
|	getSubs(Br((a, x), t1, t2), b) = 
		if b < a then getSubs(t1, b)
		else if a < b then getSubs(t2, b)
		else (*a=b*) (t1, t2);

(*actual delete function*)
fun delete(t, b) = insertList(mergeTrees(getSubs(t, b)), deleteWM(t, b));