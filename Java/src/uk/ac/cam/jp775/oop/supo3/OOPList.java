package uk.ac.cam.jp775.oop.supo3;

public interface OOPList<E> {

	public void add(E x);

	public void remove(E x);

	public void set(E x, int index);

	public E get(int index);

	public int size();

}
