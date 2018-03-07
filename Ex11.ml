fun member(_, []) = false
|   member(c, x::xs) = c = x orelse member(c, xs);

fun subset([], _) = true
|   subset(x::xs, ys) = member(x, ys) andalso subset(xs, ys);

fun union([], ys) = ys
|   union(x::xs, ys) = if member(x, ys) then union(xs, ys) else x::union(xs, ys);

fun inter([], ys) = []
|   inter(x::xs, ys) = if member(x, ys) then x::inter(xs, ys) else inter(xs, ys);