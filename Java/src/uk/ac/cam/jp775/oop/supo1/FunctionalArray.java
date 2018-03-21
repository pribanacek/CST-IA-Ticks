package uk.ac.cam.jp775.oop.supo1;

public class FunctionalArray {

	private int length;
	private BinaryTreeNode hd;

	public FunctionalArray(int size) {
		length = size;
		SearchSet s = new SearchSet();
		for (int i = 0; i < size; i++) {
			s.insert(0); // SearchSet function has been modified to accept repeated values
		}
		hd = s.mHead;
	}

}
