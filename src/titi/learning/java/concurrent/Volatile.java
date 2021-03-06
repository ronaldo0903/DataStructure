package titi.learning.java.concurrent;

/*
 * A simple test app to demo the usage of volatile
 * 假如有两个线程分别读写 volatile 变量时，线程 A 写入了某 volatile 变量，线程 B 在读取该 volatile 变量时，便能看到线程 A 对该 volatile 变量的写入操作，
 * 关键在这里，它不仅会看到对该 volatile 变量的写入操作，A 线程在写 volatile 变量之前所有可见的共享变量，在 B 线程读同一个 volatile 变量后，都将立即变得对 B 线程可见。" 
 * 我在想, 为什么"共享变量在B线程读同一个volatile 变量后(这个是重点)，将立即变得对 B 线程可见呢"?
 * 看到这一系列文章后面所写的的深入Java内存模型, 我大概才懂了.有可能是线程的工作内存里的volatile变量备份即使是更新了, 也不会马上写到主内存里, 只有在主内存去主动读取这个volatile变量的情况下, 才会把工作内存里的volatile变量备份写入主内存里, 而且顺便把工作内存里的非volatile变量备份也写入到主内存里.
 * 
 * 所以，本示例中在workMethod()中交换volatile变量(missedIt)和非volatile变量(value)的赋值顺序，会影响程序的最终输出
 */
public class Volatile extends Object implements Runnable {
	private int value;
	private volatile boolean missedIt;
	private long creationTime;

	public Volatile() {
		value = 10;
		missedIt = false;
		creationTime = System.currentTimeMillis();
	}

	public void run() {
		print("entering run()");

		// 循环检查value的值是否不同
		while (value < 20) {
			print("in run() -- in the while loop");
			if (missedIt) {
				int currValue = value;
				Object lock = new Object();
				synchronized (lock) {
				}
				int valueAfterSync = value;
				print("in run() - see value=" + currValue + ", but rumor has it that it changed!");
				print("in run() - valueAfterSync=" + valueAfterSync);
				//break;
			}
			try {
				Thread.sleep(10);
			} catch (Exception e) {

			}
		}
		print("leaving run()");
	}

	public void workMethod() throws InterruptedException {
		print("entering workMethod()");
		print("in workMethod() - about to sleep for 2 seconds");
		Thread.sleep(2000);

		/*value = 50;
		print("in workMethod() - just set value=" + value);*/
		missedIt = true;
		print("in workMethod() - just set missedIt=" + missedIt);

		print("in workMethod() - about to sleep for 5 seconds");
		Thread.sleep(10000);
		// 仅在此改变missedIt的值
		/*missedIt = true;
		print("in workMethod() - just set missedIt=" + missedIt);*/
		value = 50;
		print("in workMethod() - just set value=" + value);

		print("in workMethod() - about to sleep for 3 seconds");
		Thread.sleep(3000);
		print("leaving workMethod()");
	}

	/*
	 * 该方法的功能是在要打印的msg信息前打印出程序执行到此所化去的时间，以及打印msg的代码所在的线程
	 */
	private void print(String msg) {
		// 使用java.text包的功能，可以简化这个方法，但是这里没有利用这一点
		long interval = System.currentTimeMillis() - creationTime;
		String tmpStr = "    " + (interval / 1000.0) + "000";
		int pos = tmpStr.indexOf(".");
		String secStr = tmpStr.substring(pos - 2, pos + 4);
		String nameStr = "        " + Thread.currentThread().getName();
		nameStr = nameStr.substring(nameStr.length() - 8, nameStr.length());
		System.out.println(secStr + " " + nameStr + ": " + msg);
	}

	public static void main(String[] args) {
		try {
			// 通过该构造函数可以获取实时时钟的当前时间
			Volatile vol = new Volatile();

			// 稍停100ms，以让实时时钟稍稍超前获取时间，使print（）中创建的消息打印的时间值大于0
			Thread.sleep(100);

			Thread t = new Thread(vol);
			t.start();

			// 休眠100ms，让刚刚启动的线程有时间运行
			Thread.sleep(5000);
			// workMethod方法在main线程中运行
			vol.workMethod();
		} catch (InterruptedException x) {
			System.err.println("one of the sleeps was interrupted");
		}
	}
}
