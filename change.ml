fun change(till, 0) = []
|   change(c::till, amt) = 
	if amt<c then change(till, amt)
	else c::change(c::till, amt-c);