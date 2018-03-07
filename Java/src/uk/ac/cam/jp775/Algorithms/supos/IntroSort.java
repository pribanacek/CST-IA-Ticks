package uk.ac.cam.jp775.Algorithms.supos;

import java.util.Arrays;

public class IntroSort {

	public static void introsort(int[] a) {
		int depth = ((int) Math.log(a.length)) * 2;
		sort(a, depth, 0, a.length - 1);
	}

	private static void sort(int[] a, int d, int start, int end) {
		int length = a.length;
		if (length <= 1) {
			return;
		} else if (d == 0) {
			heapSort(a, start, end);
		} else {
			if (start >= end)
				return;
			int pivot = a[(start + end) / 2];
			int index = partition(a, start, end, pivot);
			sort(a, d - 1, start, index - 1);
			sort(a, d - 1, index, end);
		}
	}

	private static void heapSort(int[] a, int start, int end) {
		for (int i = end / 2 - 1; i >= start; i--)
			heapify(a, end, i);
		for (int i = end - 1; i >= start; i--) {
			int temp = a[start];
			a[start] = a[i];
			a[i] = temp;
			heapify(a, i, start);
		}
	}

	private static void heapify(int[] a, int n, int i) {
		int largest = i;
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		if (left < n && a[left] > a[largest])
			largest = left;
		if (right < n && a[right] > a[largest])
			largest = right;
		if (largest != i) {
			int temp = a[i];
			a[i] = a[largest];
			a[largest] = temp;
			heapify(a, n, largest);
		}
	}

	private static int partition(int[] a, int start, int end, int pivot) {
		while (start <= end) {
			while (a[start] < pivot) {
				start++;
			}
			while (a[end] > pivot) {
				end--;
			}
			if (start <= end) {
				int temp = a[start];
				a[start] = a[end];
				a[end] = temp;
				start++;
				end--;
			}
		}
		return start;
	}

	public static void main(String[] args) {
		int[] values = new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		// for (int i = 0; i < values.length; i++) {
		// values[i] = (new Random()).nextInt(100);
		// }
		System.out.println("Input:\t" + Arrays.toString(values));
		introsort(values);
		System.out.println("Output:\t" + Arrays.toString(values));
	}

}
