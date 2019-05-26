package titi.learning.java.jvm.classloader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

public class HotdeployTest {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InterruptedException {
		while (true) {
		      ClassLoader loader = new ClassLoader() {
		        @Override
		        public Class<?> loadClass(String name) throws ClassNotFoundException {
		          try {
		            String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";

		            InputStream is = getClass().getResourceAsStream(fileName);
		            if (is == null) {
		              return super.loadClass(name);
		            }

		            byte[] b = new byte[is.available()];

		            is.read(b);
		            return defineClass(name, b, 0, b.length);

		          } catch (IOException e) {
		            e.printStackTrace();
		            throw new ClassNotFoundException(name);
		          }
		        }
		      };
		      
		      Class clazz = loader.loadClass("titi.learning.java.jvm.classloader.Account");
		      Object account = clazz.newInstance();
		      account.getClass().getMethod("operation", new Class[]{}).invoke(account);
		      Thread.sleep(20000);
		    }

	}

}
