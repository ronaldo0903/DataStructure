package titi.learning.designpattern.singleton;

public class SimpleSingleton {
	private static SimpleSingleton instance = new SimpleSingleton();
	private SimpleSingleton() {}
	public static SimpleSingleton getInstance() {
		return instance;
	}
}
