package uk.ac.cam.jp775.oop.supo2;

public class OOPArrayQueue implements OOPQueue {

	private int head = 0;
	private int tail = 0;
	private Integer[] array = new Integer[1];

	private void grow() {
		Integer[] newArray = new Integer[array.length * 2];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}

	/**
	 * Makes space at the end of the array
	 */
	private void shift() {
		for (int i = 0; i < size(); i++) {
			array[i] = array[head + i];
		}
		tail = size() - 1;
		head = 0;
	}

	@Override
	public void add(int x) {
		if (tail >= array.length - 1) {
			if (head > 0) {
				shift();
			} else {
				grow();
			}
		}
		array[++tail] = x;
	}

	@Override
	public int get() {
		return array[head];
	}

	@Override
	public int remove() {
		int val = array[head];
		array[head] = null;
		head++;
		return val;
	}

	@Override
	public int size() {
		return tail - head;
	}

}
