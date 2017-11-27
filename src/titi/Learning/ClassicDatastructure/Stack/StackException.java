package titi.Learning.ClassicDatastructure.Stack;

public class StackException extends Exception {
	private StackExceptionEnum exceptionType;
	private int maxCapacity;
	public StackException(StackExceptionEnum type, int capacity) {
		exceptionType = type;
		maxCapacity = capacity;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Stack");
		switch(exceptionType) {
		case OVERFLOW:
			sb.append("OverFlow");
			break;
		case UNDERFLOW:
			sb.append("UnderFlow");
			break;
		default:
			break;
		}
		sb.append(" Exception: capacity is " + maxCapacity);
		return sb.toString();
	}
}