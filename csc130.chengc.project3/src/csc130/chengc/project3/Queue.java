package csc130.chengc.project3;

public interface Queue<T> {
	void enqueue(T data) throws RuntimeException;

	T dequeue() throws RuntimeException;

	T front() throws RuntimeException;

	int getSize();

	boolean isFull();

	boolean isEmpty();
}
