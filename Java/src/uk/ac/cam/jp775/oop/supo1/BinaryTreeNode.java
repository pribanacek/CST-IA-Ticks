package uk.ac.cam.jp775.oop.supo1;

public class BinaryTreeNode {

	private int mValue;
	private BinaryTreeNode mLeft;
	private BinaryTreeNode mRight;

	public BinaryTreeNode(int x) {
		mValue = x;
	}

	public int getValue() {
		return mValue;
	}

	public void setValue(int x) {
		mValue = x;
	}

	public BinaryTreeNode getLeft() {
		return mLeft;
	}

	public BinaryTreeNode getRight() {
		return mRight;
	}

	public void setLeft(BinaryTreeNode n) {
		mLeft = n;
	}

	public void setRight(BinaryTreeNode n) {
		mRight = n;
	}

}
