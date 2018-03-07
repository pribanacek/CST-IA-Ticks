fun split([]) = ([], [])
|   split(x::xs) = 
	let val (negs, nonnegs) = split(xs)
	in if (x < 0) then (negs, x::nonnegs) else (x::negs, nonnegs) end;