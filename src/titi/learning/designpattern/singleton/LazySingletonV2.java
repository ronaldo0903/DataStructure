package titi.learning.designpattern.singleton;

/*
 * 单例模式懒汉式V2
 * 优点:使用静态内部类机制保证了instance只在第一次getInstance()方法被调用时被创建一次，而且避免了多线程的并发冲突
 * 不足:在反射的作用下，单例会被破坏 
 */
public class LazySingletonV2 {
	private LazySingletonV2() {}
	static class SingletonHolder {
		private static final LazySingletonV2 instance = new LazySingletonV2();
	}
	public static LazySingletonV2 getInstance() {
		return SingletonHolder.instance;
	}
}
