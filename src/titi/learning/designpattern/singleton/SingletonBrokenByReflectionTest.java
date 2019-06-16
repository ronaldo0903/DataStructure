package titi.learning.designpattern.singleton;

import java.lang.reflect.Constructor;

public class SingletonBrokenByReflectionTest {

	public static void main(String[] args) throws Exception {

		TestSingletonAfterReflection(LazySingletonV2.class);
		TestSingletonAfterReflection(LazySingletonV3.class);
	}

	private static void TestSingletonAfterReflection(Class clazz) throws Exception {
		assert clazz == LazySingletonV2.class || clazz == LazySingletonV3.class;
        System.out.println("Testing Reflection behavior for class:" + clazz.getSimpleName());
		Object instance1 = clazz.getDeclaredMethod("getInstance", null).invoke(clazz, null);
        //通过反射创建第二个实例
		Object instance2 = null;
        try {
            Constructor<?> cons = clazz.getDeclaredConstructor();
            cons.setAccessible(true);
            instance2 = cons.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //检查两个实例的hash值
        System.out.println("Instance 1 loadbalance:" + instance1.hashCode());
        System.out.println("Instance 2 loadbalance:" + instance2.hashCode());
        System.out.println("=================================");
	}

}
