package titi.learning.ClassicDatastructure.Stack;

import java.lang.reflect.Array;

public class ArrayStack<T> implements Stack<T> {
	private Class<T> type;
	private int top;
	private int capacity;
	private T[] array;
	public ArrayStack(Class<T> type){
		this(type,1);
	}
	
	public ArrayStack(Class<T> type, int cap){
		this.type = type;
		capacity = cap;
		array = (T[])Array.newInstance(type, capacity);
		top = -1;
	}

	@Override
	public void push(T data) throws StackException {
		// TODO Auto-generated method stub
		if(!isFull()) {
			array[++top] = data;
		}
		else {
			throw new StackException(StackExceptionEnum.OVERFLOW, capacity);
		}		
	}

	@Override
	public T pop() throws StackException {
		// TODO Auto-generated method stub
		if(isEmpty()) throw new StackException(StackExceptionEnum.UNDERFLOW, capacity);
		return array[top--];
	}

	@Override
	public T top() {
		// TODO Auto-generated method stub
		if(isEmpty()) return null;
		return array[top];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return top + 1;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (top == -1);
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return size() == capacity;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		top = -1;
	}

}
