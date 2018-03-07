fun sum([]) = 0
| sum(x::xs) = x + sum(xs);

fun sumit([], s) = s
| sumit(x::xs, s) = sumit(xs, x + s);