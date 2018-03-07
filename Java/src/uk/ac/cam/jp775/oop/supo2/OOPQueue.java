package uk.ac.cam.jp775.oop.supo2;

public interface OOPQueue {

	/*
	 * Adds x to the end of the queue
	 */
	public void add(int x);

	/*
	 * Retrieves and but doesn't remove head of queue
	 */
	public int get();

	/*
	 * Retrieves and removes head of queue
	 */
	public int remove();

	public int size();

}
