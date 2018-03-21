package uk.ac.cam.jp775.oop.supo3;

import java.util.Arrays;

public class MyClass implements Cloneable {

	private String mName;
	private int[] mData;

	public MyClass(MyClass toCopy) {
		this.mName = toCopy.mName;
		this.mData = toCopy.mData.clone();
	}

	@Override
	public Object clone() {
		return new MyClass(this);
	}

	public MyClass(String name, int[] data) {
		this.mName = name;
		this.mData = data;
	}

	public static void main(String[] args) {
		int[] x = { 1, 3, 7, 8, 9, 10 };
		MyClass a = new MyClass("John", x);
		MyClass b = (MyClass) a.clone();
		x[5] = 100;
		System.out.println(Arrays.toString(a.mData));
		System.out.println(Arrays.toString(b.mData));
	}

}
