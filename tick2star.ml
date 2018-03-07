fun allcons(a, [], l) = l
|   allcons(a, x::xs : 'a list list, l) = allcons(a, xs, (a::x)::l);

fun concat([], ys) = ys
|   concat(x::xs : 'a list list, ys) = concat(xs, x::ys);