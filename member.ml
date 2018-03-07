fun member(x, []) = false
|   member(x, y::l) = (x = y) orelse member(x, l);

fun union([], l) = l
|   union(x::xs, l) = if member(x, l) then union(xs, l) else [x] @ union(xs, l);