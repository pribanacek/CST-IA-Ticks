package uk.ac.cam.jp775.Algorithms.tick1;

import uk.ac.cam.rkh23.Algorithms.Tick1.EmptyHeapException;
import uk.ac.cam.rkh23.Algorithms.Tick1.MaxCharHeapInterface;

public class MaxCharHeap implements MaxCharHeapInterface {

	private int heapSize = 0;
	private char[] data;

	public MaxCharHeap(String s) {
		if (s != null && s.length() > 0) {
			data = s.toLowerCase().toCharArray();
			heapSize = data.length;
			for (int i = heapSize / 2; i >= 0; i--) {
				heapify(i);
			}
		} else {
			data = new char[1]; // prevents doubling zero
		}
	}

	@Override
	public char getMax() throws EmptyHeapException {
		if (data == null || heapSize <= 0) {
			throw new EmptyHeapException();
		}
		char max = data[0];
		data[0] = data[heapSize - 1]; // swap first with last (kind of)
		data[heapSize - 1] = 0;
		heapify(0);
		heapSize--;
		return max;
	}

	/**
	 * checks if the node at i satisfies the max tree condition
	 */
	private boolean isNodeValid(int i) {
		if (i + 1 > heapSize / 2)
			return true; // leaves of the tree
		return data[i] >= data[2 * i + 1] && data[i] >= data[2 * i + 2];
	}

	private void heapify(int root) {
		if (!isNodeValid(root)) {
			// j points to larger of 2 children
			int j;
			if (heapSize <= 2 * root + 2) { // case of node with only one child
				j = 2 * root + 1;
			} else {
				j = data[2 * root + 1] > data[2 * root + 2] ? 2 * root + 1 : 2 * root + 2;
			}

			char temp = data[root];
			data[root] = data[j];
			data[j] = temp;
			heapify(j);
		}
	}

	@Override
	public void insert(char c) {
		c = Character.toLowerCase(c);
		if (heapSize >= data.length) {
			expandArray();
		}

		// put item to the bottom of the tree
		data[heapSize] = c;

		// swap upwards until it's in the right place
		int i = heapSize;
		char parent = data[(i - 1) / 2];
		while (i > 0 && parent < c) {
			data[(i - 1) / 2] = data[i];
			data[i] = parent;
			i = (i - 1) / 2;
			parent = data[(i - 1) / 2];
		}
		heapSize++;
	}

	@Override
	public int getLength() {
		return heapSize;
	}

	private void expandArray() {
		char[] newData = new char[data.length * 2];
		for (int i = 0; i < heapSize; i++) {
			newData[i] = data[i];
		}
		data = newData;
	}
}
