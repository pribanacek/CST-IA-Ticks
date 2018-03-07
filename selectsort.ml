(*fun least([], y, l) = (y, l)
|   least(x::xs, y, l) = 
        if (x < y orelse y < 0) then (least(xs, x), ) else least(xs, y);
(*assuming a list of positive integers*)

fun selectsort([]) = []
|   selectsort(x::xs) = least(x::xs, ~1) :: selectsort(xs);*)

fun selectsort([]) = []
|   selectsort(x::xs) =
    let
      fun select small ([], output) = small::(selectsort(output))
      |   select small (x::xs, output) =
            if (x < small) then
              select x (xs, small::output)
            else
              select small (xs, x::output)
    in
      select x (xs, [])
    end;