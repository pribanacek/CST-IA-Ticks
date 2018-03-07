fun powerit(x : real, n, p) = 
	if n = 0 then p
	else if n mod 2 = 0 then powerit(x * x, n div 2, p)
		 else powerit(x * x, n div 2, p * x);

fun power(x, n) = powerit(x, n, 1.0);