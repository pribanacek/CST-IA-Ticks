fun allc [] = []
|   allc(cs::css) = (1::cs)::allc css;

fun map f [] = []
|   map f (x::xs) = (f x) :: map f xs;

