datatype arithmetic = Real of real
			| Var of string
			| Minus of real
			| Plus of real * real
			| Times of real * real;
exception Variable of string;
			
			
fun eval(Real r) = r
|   eval(Var s) = raise (Variable s)
|   eval(Minus r) = ~r
|   eval(Plus(x, y)) = x + y
|   eval(Times(x, y)) = x * y;