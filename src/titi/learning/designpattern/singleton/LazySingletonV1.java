package titi.learning.designpattern.singleton;

/*
 * 单例模式懒汉式V1
 * 使用了synchronized修饰符多多少少影响了性能
 */
public class LazySingletonV1 {
	private static LazySingletonV1 instance;
	private LazySingletonV1(){}
	public static LazySingletonV1 getInstance() {
		if(instance == null) {
			synchronized(LazySingletonV1.class) {
				if(instance == null) {
					instance = new LazySingletonV1();
				}
			}
		}
		return instance;
	}
}
