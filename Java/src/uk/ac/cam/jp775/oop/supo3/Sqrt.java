package uk.ac.cam.jp775.oop.supo3;

public class Sqrt {

	public static double sqrt(double a) {
		if (a < 0.0D) {
			throw new IllegalArgumentException();
		}
		double x = guess(a, 1.0D);

		// level of accuracy could be an argument
		for (int i = 0; i < 10; i++) {
			x = guess(a, x);
		}
		return x;
	}

	private static double guess(double a, double x) {
		assert (a > 0 && x > 0);
		// f(x) = x^2 - a
		// f'(x) = 2x
		return x - (x * x - a) / (2 * x);
	}

	public static void main(String[] args) {
		System.out.println("adefqaedfv" + f());

	}

	public static int f() {
		int x = 0;
		try {
			x = 12;
			return x;
		} finally {
			x += 16;
			System.out.println("Finally takes precedence");
		}
	}

}
