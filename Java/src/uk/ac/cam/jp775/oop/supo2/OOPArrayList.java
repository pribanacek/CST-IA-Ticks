package uk.ac.cam.jp775.oop.supo2;

public class OOPArrayList implements OOPList {

	private int size = 0;
	private Integer[] array = new Integer[1];

	private void doubleSpace() {
		Integer[] newArray = new Integer[array.length * 2];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}

	@Override
	public void add(int x) {
		array[size - 1] = x;
		if (++size >= array.length) {
			doubleSpace();
		}
	}

	@Override
	public void remove(int index) {
		for (int i = index; i < size - 1; i++) {
			array[i] = array[i + 1];
		}
		array[size - 1] = null;
		size--;
	}

	@Override
	public void set(int x, int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		array[index] = x;
	}

	@Override
	public int get(int index) {
		return array[index];
	}

	@Override
	public int size() {
		return size;
	}

}
