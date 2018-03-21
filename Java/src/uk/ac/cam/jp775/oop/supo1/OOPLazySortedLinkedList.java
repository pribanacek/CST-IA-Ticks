package uk.ac.cam.jp775.oop.supo1;

public class OOPLazySortedLinkedList {

	private OOPLinkedListElement hd;

	// private void sort() {
	// if (hd == null)
	// return;
	// int l = length();
	// OOPLinkedListElement t = null;
	// for (int i = 0; i < l - 1; i++) {
	// OOPLinkedListElement e = hd;
	// for (int j = 0; j < l - 1; j++) {
	// if (e.get() < e.getNext().get()) {
	// t = e.getNext();
	// e.setNext(t.getNext());
	// t.setNext(e);
	// e = e.getNext();
	// }
	// }
	// hd = t;
	// }
	// }

	public void insert(int x) {
		hd = new OOPLinkedListElement(x, hd);
	}

	public void removeHead() {
		hd = hd.getNext();
	}

	public int getHead() {
		if (hd == null) {
			return 0;
		}
		int smallest = hd.get();
		OOPLinkedListElement e = hd;
		while (e != null) {
			if (smallest > e.get()) {
				smallest = e.get();
			}
			e = e.getNext();
		}
		return smallest;
	}

	public int getValue(int index) {
		OOPLinkedListElement e = hd;
		for (int i = 0; i < index; i++) {
			e = e.getNext();
		}
		return e.get();
	}

	public int length() {
		int len = 0;
		OOPLinkedListElement e = hd;
		while (e.getNext() != null) {
			e = e.getNext();
			len++;
		}
		return len;
	}

}
