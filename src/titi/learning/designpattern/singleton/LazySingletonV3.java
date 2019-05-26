package titi.learning.designpattern.singleton;

import java.io.Serializable;

/*
 * 保证反射机制下的单例性
 */
public class LazySingletonV3 implements Serializable {
	private static boolean initialized = false;
	private LazySingletonV3() {
		synchronized(LazySingletonV3.class) {
			if(initialized == false) {
				initialized = !initialized;
			}
			else {
				throw new RuntimeException("Singleton gets destroyed!");
			}
		}
	}
	static class SingletonHolder {
		private static final LazySingletonV3 instance = new LazySingletonV3();
	}
	public static LazySingletonV3 getInstance() {
		return SingletonHolder.instance;
	}
 }
