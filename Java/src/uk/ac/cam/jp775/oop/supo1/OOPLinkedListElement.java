package uk.ac.cam.jp775.oop.supo1;

public class OOPLinkedListElement {

	public OOPLinkedListElement(OOPLinkedListElement e) {
		this.value = e.value;
		e.next = new OOPLinkedListElement(e.next);
	}

	private int value;
	private OOPLinkedListElement next;

	public OOPLinkedListElement(int x, OOPLinkedListElement next) {
		this.value = x;
		this.next = next;
	}

	public int get() {
		return value;
	}

	public void set(int val) {
		this.value = val;
	}

	public OOPLinkedListElement getNext() {
		return next;
	}

	public void setNext(OOPLinkedListElement n) {
		this.next = n;
	}

}
