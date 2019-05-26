package titi.learning.designpattern.singleton;

import java.io.Serializable;

public class LazySingletonV4 implements Serializable {
	private static boolean initialized = false;
    private LazySingletonV4() {
        synchronized (LazySingletonV4.class) {
            if (initialized == false) {
                initialized = !initialized;
            } else {
                throw new RuntimeException("Singleton gets destroyed!");
            }
        }
    }
    static class SingletonHolder {
        private static final LazySingletonV4 instance = new LazySingletonV4();
    }
    public static LazySingletonV4 getInstance() {
        return SingletonHolder.instance;
    }
    private Object readResolve() {
        return getInstance();
    }
}
