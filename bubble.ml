fun bubble([]) = []
|   bubble(x::y::xs) = if (x < y) then x::bubble(y::xs) else y::bubble(x::xs)
|   bubble(x::xs) = [x];

fun bsort(xs, 0) = xs
|   bsort(xs, n) = bsort(bubble(xs), n - 1);