fun last([]) = raise Empty
|   last(x::y::xs) = last(y::xs)
|   last(x::xs) = x;

fun butlastit([], _) = raise Empty
|   butlastit(x::y::xs, l) = butlastit(y::xs, l@[x])
|   butlastit(x::xs, l) = l;

fun butlast(xs) = butlastit(xs, []);

fun nth([], _) = raise Empty
|   nth(x::xs, 0) = x
|   nth(x::xs, n) = nth(xs, n - 1);