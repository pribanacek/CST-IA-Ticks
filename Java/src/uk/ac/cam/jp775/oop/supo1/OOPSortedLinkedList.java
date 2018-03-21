package uk.ac.cam.jp775.oop.supo1;

public class OOPSortedLinkedList {

	private OOPLinkedListElement hd;

	public void insert(int x) {
		if (hd == null) {
			hd = new OOPLinkedListElement(x, null);
		} else if (x < hd.get()) {
			hd = new OOPLinkedListElement(x, hd);
		} else {
			OOPLinkedListElement e = hd;
			while (e != null) {

				OOPLinkedListElement next = e.getNext();
				if (x >= e.get() && (next == null || x <= next.get())) {
					e.setNext(new OOPLinkedListElement(x, next));
					break;
				}
				e = e.getNext();
			}
		}
	}

	public void removeHead() {
		hd = hd.getNext();
	}

	public void setHead(int x) {
		hd = new OOPLinkedListElement(x, hd);
	}

	public int getHead() {
		return hd.get();
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
