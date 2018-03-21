package uk.ac.cam.jp775.oop.supo3;

public class OOPLinkedListElement<E> {

	public OOPLinkedListElement(OOPLinkedListElement<E> e) {
		this.value = e.value;
		e.next = new OOPLinkedListElement<E>(e.next);
	}

	private E value;
	private OOPLinkedListElement<E> next;

	public OOPLinkedListElement(E x, OOPLinkedListElement<E> next) {
		this.value = x;
		this.next = next;
	}

	public E get() {
		return value;
	}

	public void set(E val) {
		this.value = val;
	}

	public OOPLinkedListElement<E> getNext() {
		return next;
	}

	public void setNext(OOPLinkedListElement<E> n) {
		this.next = n;
	}

}
