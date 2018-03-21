package uk.ac.cam.jp775.oop.supo1;

public class SearchSet {

	private int mElements;
	public BinaryTreeNode mHead;

	public SearchSet() {
	}

	public void insert(int x) {
		if (mHead == null) {
			mHead = new BinaryTreeNode(x);
			return;
		}
		BinaryTreeNode n = mHead;
		while (n != null) {
			if (x < n.getValue()) {
				if (n.getLeft() == null) {
					n.setLeft(new BinaryTreeNode(x));
					break;
				} else {
					n = n.getLeft();
				}
			} else if (x > n.getValue()) {
				if (n.getRight() == null) {
					n.setRight(new BinaryTreeNode(x));
				} else {
					n = n.getRight();
				}
			} else
				break;
		}
	}

	public int getNumberElements() {
		return mElements;
	}

	public boolean contains(int x) {
		boolean flag = false;
		BinaryTreeNode n = mHead;
		while (n != null) {
			if (x > n.getValue()) {
				n = n.getRight();
			} else if (x < n.getValue()) {
				n = n.getLeft();
			} else {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		SearchSet s = new SearchSet();
		s.insert(5);
		s.insert(7);
		s.insert(1);
		s.insert(4);
		System.out.println(s.contains(4));
	}
}
