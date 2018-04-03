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
fun map1 f (Cons(x, fx)) = Cons(f x, fn() => map1 f (fx()));
fun map2 f (Cons(x, fx)) (Cons(y, fy)) = Cons(f x y, fn() => map2 f (fx()) (fy()));


(*Star*)

fun merge(Cons(x, fx), Cons(y, fy)) = 
        if x = y then Cons(x, fn () => merge(fx(), fy()) )
        else if x < y then Cons(x, fn() => merge(Cons(y, fy), fx()) )
        else Cons(y, fn() => merge(Cons(x, fx), fy()));

fun fun23() = Cons(1, fn() => 
                merge(map1 (fn i => i * 2) (fun23()),
                      map1 (fn i => i * 3) (fun23())));


val pows23 = fun23();

fun fun235() = Cons(1, fn() => 
                merge(merge(map1 (fn i => i * 2) (fun235()),
                            map1 (fn i => i * 3) (fun235())),
                            map1 (fn i => i * 5) (fun235())));
val pows235 = fun235();
