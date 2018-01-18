package titi.Learning.java.concurrent;

/*
 * A simple test app to demo the usage of volatile
 * �����������̷ֱ߳��д volatile ����ʱ���߳� A д����ĳ volatile �������߳� B �ڶ�ȡ�� volatile ����ʱ�����ܿ����߳� A �Ը� volatile ������д�������
 * �ؼ�������������ῴ���Ը� volatile ������д�������A �߳���д volatile ����֮ǰ���пɼ��Ĺ���������� B �̶߳�ͬһ�� volatile �����󣬶���������ö� B �߳̿ɼ���" 
 * ������, Ϊʲô"���������B�̶߳�ͬһ��volatile ������(������ص�)����������ö� B �߳̿ɼ���"?
 * ������һϵ�����º�����д�ĵ�����Java�ڴ�ģ��, �Ҵ�ŲŶ���.�п������̵߳Ĺ����ڴ����volatile�������ݼ�ʹ�Ǹ�����, Ҳ��������д�����ڴ���, ֻ�������ڴ�ȥ������ȡ���volatile�����������, �Ż�ѹ����ڴ����volatile��������д�����ڴ���, ����˳��ѹ����ڴ���ķ�volatile��������Ҳд�뵽���ڴ���.
 * 
 * ���ԣ���ʾ������workMethod()�н���volatile����(missedIt)�ͷ�volatile����(value)�ĸ�ֵ˳�򣬻�Ӱ�������������
 */
public class Volatile extends Object implements Runnable {  
	// value����û�б����Ϊvolatile
	private int value;
	// missedIt���������Ϊvolatile
	private volatile boolean missedIt;
	// creationTime����Ҫ����Ϊvolatile����Ϊ����ִ������û�з����仯
	private long creationTime;

	public Volatile() {
		value = 10;
		missedIt = false;
		// ��ȡ��ǰʱ�䣬�༴����Volatile���캯��ʱ��ʱ��
		creationTime = System.currentTimeMillis();
	}

	public void run() {
		print("entering run()");

		// ѭ�����value��ֵ�Ƿ�ͬ
		while (value < 20) {
			// ���missedIt��ֵ���޸�Ϊtrue����ͨ��break�˳�ѭ��
			if (missedIt) {
				// ����ͬ�������ǰ����value��ֵ����currValue
				int currValue = value;
				// ��һ�����������ִ��ͬ����䣬Ŀ����Ϊ���ø��߳��ڽ�����뿪ͬ�������ʱ��
				// �����߳��е����б�����˽�п����빲���ڴ��е�ԭʼֵ���бȽϣ�
				// �Ӷ�����û����volatile��ǵı����������ı仯
				Object lock = new Object();
				synchronized (lock) {
					// �����κ���
				}
				// �뿪ͬ�������󣬽���ʱvalue��ֵ����valueAfterSync
				int valueAfterSync = value;
				print("in run() - see value=" + currValue + ", but rumor has it that it changed!");
				print("in run() - valueAfterSync=" + valueAfterSync);
				break;
			}
		}
		print("leaving run()");
	}

	public void workMethod() throws InterruptedException {
		print("entering workMethod()");
		print("in workMethod() - about to sleep for 2 seconds");
		Thread.sleep(2000);
		// ���ڴ˸ı�value��ֵ
		//value = 50;
		missedIt = true;
		print("in workMethod() - just set value=" + value);
		print("in workMethod() - about to sleep for 5 seconds");
		Thread.sleep(5000);
		// ���ڴ˸ı�missedIt��ֵ
		//missedIt = true;
		value = 50;
		print("in workMethod() - just set missedIt=" + missedIt);
		print("in workMethod() - about to sleep for 3 seconds");
		Thread.sleep(3000);
		print("leaving workMethod()");
	}

	/*
	 * �÷����Ĺ�������Ҫ��ӡ��msg��Ϣǰ��ӡ������ִ�е�������ȥ��ʱ�䣬�Լ���ӡmsg�Ĵ������ڵ��߳�
	 */
	private void print(String msg) {
		// ʹ��java.text���Ĺ��ܣ����Լ������������������û��������һ��
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
			// ͨ���ù��캯�����Ի�ȡʵʱʱ�ӵĵ�ǰʱ��
			Volatile vol = new Volatile();

			// ��ͣ100ms������ʵʱʱ�����Գ�ǰ��ȡʱ�䣬ʹprint�����д�������Ϣ��ӡ��ʱ��ֵ����0
			Thread.sleep(100);

			Thread t = new Thread(vol);
			t.start();

			// ����100ms���øո��������߳���ʱ������
			Thread.sleep(100);
			// workMethod������main�߳�������
			vol.workMethod();
		} catch (InterruptedException x) {
			System.err.println("one of the sleeps was interrupted");
		}
	}
}
