package uk.ac.cam.jp775.oop.supo1;

public class Lec1 {

	public static void main(String[] args) {
		OOPLazySortedLinkedList l = new OOPLazySortedLinkedList();
		l.insert(5);
		l.insert(8);
		l.insert(0);
		// l.insert(-12);
		// l.insert(136);
		// l.insert(2);
		for (int i = 0; i < 3; i++) {
			System.out.println(l.getHead());
			l.removeHead();
		}
	}

	public static void f(int[] a) {
		a = new int[] { 2, 3 };
	}

	public static void g(int[] a) {
		a[0] = 3;
	}

	public static int lowestCommon(long a, long b) {
		int pos = -1;
		for (int i = 0; i < 63; i++) {
			if (((a >>> i) & 1) == ((b >>> i) & 1)) {
				pos = i;
				break;
			}
		}
		return pos;
	}

	public float[][] getMatrix(int n, int m) {
		return new float[n][m];
	}

	public float[][] transpose(float[][] matrix) {
		float[][] newMatrix = new float[matrix[0].length][matrix.length];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				newMatrix[j][i] = matrix[i][j];
			}
		}

		return newMatrix;
	}

}
