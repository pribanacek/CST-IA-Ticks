fun e(n, i, t, sum) = if (i>=n) then sum else e(n, i + 1, t / real(i), sum + t);

fun eapprox(n) = e(n + 1, 1, 1.0, 0.0);

fun expit(n, i, z, t, sum) = if (i>=n) then sum else expit(n, i + 1, z, t * z / real(i), sum + t);

fun exp(z, n) = expit(n + 1, 1, z, 1.0, 0.0);


fun gcd(1, 1) = 1
|   gcd(a, b) = if a = b then a
				else if a mod 2 = 0 andalso b mod 2 = 0 then 2 * gcd(a div 2, b div 2)
				else if a mod 2 = 0 then gcd(a div 2, b)
				else if b mod 2 = 0 then gcd(a, b div 2)
				else if a > b then gcd(b, (a - b) div 2)
				else gcd(b, (b - a) div 2);