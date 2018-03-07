package uk.ac.cam.jp775.oop.supo1;

public class OOPLinkedList implements Cloneable {

	public OOPLinkedList() {

	}

	public OOPLinkedList(OOPLinkedList list) {
		this.hd = new OOPLinkedListElement(list.hd);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		OOPLinkedList clone = (OOPLinkedList) super.clone();
		clone.hd = new OOPLinkedListElement(this.hd);
		return clone;
	}
	// old code from previous exercises

	private OOPLinkedListElement hd;

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
