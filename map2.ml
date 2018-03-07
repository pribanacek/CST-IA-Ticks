fun map f [] = []
|   map f (x::xs) = (f x) :: map f xs;



fun map2 f [] = []
|   map2 f (y::ys) =
        let fun m f [] = [] | m f (x::xs) = (f x) :: m f xs
		in (m f y) :: map2 f ys end;


(*
datatype 'a option = NONE | SOME of 'a;

fun mapop f NONE = NONE
|   mapop f (SOME(v)) = SOME(f v);*)