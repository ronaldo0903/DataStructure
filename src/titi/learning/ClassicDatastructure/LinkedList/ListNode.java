package titi.learning.ClassicDatastructure.LinkedList;

public class ListNode<T> {
	private T data;
	private ListNode<T> next;
	public ListNode(T data) {
		this.data = data;
		next = null;
	}
	public ListNode(T data, ListNode<T> next) {
		this.data = data;
		this.next = next;
	}
	public void setData(T data) {
		this.data = data;
	}
	public T getData() {
		return this.data;
	}
	public void setNext(ListNode<T> node) {
		next = node;
	}
	public ListNode<T> getNext(){
		return this.next;
	}
}
