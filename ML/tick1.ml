fun evalquad(a, b, c, x) : real = a * x * x + b * x + c;


fun facr(0) = 1
  | facr(n) = if (n > 0) then n * facr(n - 1) else 0;
	
	
	
fun facit(0, p) = p
  | facit(n, p) = facit(n - 1, n * p);
  
fun faci(n) = if (n >= 0) then facit(n, 1) else 0;



fun sumit(x, 0) = x
  | sumit(x, n) = x + sumit(x / 2.0, n - 1);

fun sumt(n) = sumit(1.0, n - 1);

 