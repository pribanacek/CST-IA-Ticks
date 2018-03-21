package uk.ac.cam.jp775.oop.supo3;

public class SomeOtherClass implements Cloneable {

	private boolean hotOrNot;

	@Override
	public Object clone() {
		return new SomeOtherClass();
	}

}
