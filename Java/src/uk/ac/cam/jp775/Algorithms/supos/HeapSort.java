package uk.ac.cam.jp775.Algorithms.supos;

public class HeapSort {

	public static int[] sort(int[] a) {
		int end = a.length;
		for (int i = end / 2; i >= 0; i--) {
			a = heapify(a, i, 0);
		}
		for (int i = end; i > 1; i--) {
			int tmp = a[0];
			a[0] = a[i - 1];
			a[i - 1] = tmp;
			a = heapify(a, i - 1, 0);
			end -= 1;
		}
		return a;
	}

	private static int[] heapify(int[] a, int iEnd, int iRoot) {
		int root = a[iRoot];
		if (isNodeHeap(a, iEnd, iRoot)) {
			return a;
		} else {
			int j;
			if (iRoot * 2 + 2 > iEnd) {
				j = iRoot * 2 + 1;
			} else {
				j = a[iRoot * 2 + 1] > a[iRoot * 2 + 2] ? iRoot * 2 + 1 : iRoot * 2 + 2;
			}
			int tmp = a[iRoot];
			a[iRoot] = a[j];
			a[j] = tmp;
			return heapify(a, iEnd, j);
		}
	}

	private static boolean isNodeHeap(int[] a, int iEnd, int iRoot) {
		int root = a[iRoot];
		if (iRoot > iEnd / 2) {
			return true;
		}
		if (iRoot * 2 + 2 > iEnd) {
			return root > a[iRoot * 2 + 1];
		}
		return root > a[iRoot * 2 + 1] && root > a[iRoot * 2 + 2];
	}

}
