package titi.Learning.java.concurrent;

class Producer implements Runnable { // ͨ��Runnableʵ�ֶ��߳�
	private Info info = null; // ����Info����

	public Producer(Info info) {
		this.info = info;
	}

	public void run() {
		boolean flag = true; // ������λ
		for (int i = 0; i < 10; i++) {
			if (flag) {
				this.info.set("����--1", "����--1"); // ��������
				flag = false;
			} else {
				this.info.set("����--2", "����--2"); // ��������
				flag = true;
			}
		}
	}
}