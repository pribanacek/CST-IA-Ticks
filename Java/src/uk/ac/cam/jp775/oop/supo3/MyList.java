package uk.ac.cam.jp775.oop.supo3;

import java.util.AbstractList;

public class MyList<E> extends AbstractList<E> {

	@Override
	public E get(int arg0) {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

	public static void main(String[] args) {
		AbstractList<String> list = new MyList<String>();
		System.out.println(list.get(0));
	}
}
