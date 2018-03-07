package uk.ac.cam.jp775.Algorithms.supos;

public class Fibonacci {

	public static long f1(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		return f1(n - 1) + f1(n - 2);
	}

	public static long[] cache = new long[10000];

	public static long f2(int n) {
		if (n == 0) {
			return cache[0];
		}
		if (n == 1) {
			return cache[1] = 1;
		}
		long f = cache[n];
		if (f == 0) {
			f = f2(n - 1) + f2(n - 2);
			cache[n] = f;
		}
		return f;
	}

	public static void main(String[] args) {
		int n = 43;
		System.out.println("n = " + n);
		long start = System.nanoTime();
		System.out.println(f2(n));
		System.out.println("Time: " + (System.nanoTime() - start) / 1.0e9);

		start = System.nanoTime();
		System.out.println(f1(n));
		System.out.println("Time: " + (System.nanoTime() - start) / 1.0e9);
	}

}
