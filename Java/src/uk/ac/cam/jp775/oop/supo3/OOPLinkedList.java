package uk.ac.cam.jp775.oop.supo3;

public class OOPLinkedList<E> implements Cloneable {

	public OOPLinkedList() {

	}

	public OOPLinkedList(OOPLinkedList<E> list) {
		this.hd = new OOPLinkedListElement<E>(list.hd);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		OOPLinkedList<E> clone = new OOPLinkedList<>();
		clone.hd = new OOPLinkedListElement<E>(this.hd);
		return clone;
	}
	// old code from previous exercises

	private OOPLinkedListElement<E> hd;

	public void removeHead() {
		hd = hd.getNext();
	}

	public void setHead(E x) {
		hd = new OOPLinkedListElement<E>(x, hd);
	}

	public E getHead() {
		return hd.get();
	}

	public E getValue(int index) {
		OOPLinkedListElement<E> e = hd;
		for (int i = 0; i < index; i++) {
			e = e.getNext();
		}
		return e.get();
	}

	public int length() {
		int len = 0;
		OOPLinkedListElement<E> e = hd;
		while (e.getNext() != null) {
			e = e.getNext();
			len++;
		}
		return len;
	}

}
