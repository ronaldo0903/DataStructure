package titi.learning.designpattern.singleton;

import java.io.*;

public class SingletonSerizableTest {

	public static void main(String[] args) {
		try {
            TestSingletonSerizable(LazySingletonV3.class);
            TestSingletonSerizable(LazySingletonV4.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

	private static void TestSingletonSerizable(Class clazz) throws Exception {
		assert clazz == LazySingletonV3.class || clazz == LazySingletonV4.class;
		Object instance1 = clazz.getDeclaredMethod("getInstance", null).invoke(clazz, null);
		ObjectOutput out = null;
		out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
		out.writeObject(instance1);
		out.close();
		//deserialize from file to object
		ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"));
		Object instance2 = in.readObject();
		in.close();
		System.out.println("instance1 hashCode=" + instance1.hashCode());
		System.out.println("instance2 hashCode=" + instance2.hashCode());
	}

}
