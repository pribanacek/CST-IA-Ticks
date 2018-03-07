datatype 'a queue = Q of 'a list * 'a list;

fun revApp ([], ys) = ys
|   revApp (x::xs, ys) = revApp(xs, x::ys);

fun rev t = revApp(t, []);

fun norm(Q([], t)) = Q(rev t, [])
|   norm q = q;

fun qnull(Q([], [])) = true | qnull _ = false;

fun enq(Q(h, t), x) = norm(Q(h, x::t));

fun deq(Q(x::h, t)) = norm(Q(h, t));

fun breadth q = 
	if qnull q then []
	else let val x = qhd q in
		if x = Lf then breadth(deq q)
		else if x = Br(v, t, u) then v::breadth(enq(enq(deq q, t), u));
	