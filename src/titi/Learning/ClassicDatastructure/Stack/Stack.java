package titi.Learning.ClassicDatastructure.Stack;

public interface Stack<T> {
	void push(T data) throws StackException;
	T pop() throws StackException;
	T top();
	int size();
	boolean isEmpty();
	boolean isFull();
	void clear();
}
