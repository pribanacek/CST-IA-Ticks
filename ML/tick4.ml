fun twice f = (fn x => f (f x));

fun nfold f 0 = (fn x => x)
|   nfold f n = (fn x => nfold f (n - 1) (f x));

fun sum m n = nfold (fn i => i + 1) n m;

fun product m n = nfold (fn i => i + m) n 0;

fun power m n = nfold (fn i => i * m) n 1;


datatype 'a stream = Cons of 'a * (unit -> 'a stream);
fun from k = Cons(k, fn() => from(k + 1));

fun nth(Cons(v, f), 1) = v
|   nth(Cons(v, f), n) = nth(f(), n - 1);

fun sq n = Cons(n * n, fn() => sq(n + 1));

val squares = sq(1);

fun map2 f (Cons(x, fx)) (Cons(y, fy)) = Cons(f x y, fn() => map2 f (fx()) (fy()));