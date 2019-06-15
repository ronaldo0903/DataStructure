package titi.learning.java.concurrent;

import java.util.concurrent.CyclicBarrier;

class Producer implements Runnable { // 通过Runnable实现多线程
	private Info info = null; // 保存Info引用
	private CyclicBarrier barrier;

	public Producer(Info info) {
		this.info = info;
	}

	public Producer(Info info, CyclicBarrier b) {
		this.info = info;
		this.barrier = b;
	}

	public void run() {
		try {
			barrier.await();
			boolean flag = true; // 定义标记位
			for (int i = 0; i < 10; i++) {
				if (flag) {
					this.info.set("姓名--1", "内容--1"); // 设置名称
					flag = false;
				} else {
					this.info.set("姓名--2", "内容--2"); // 设置名称
					flag = true;
				}
			}
		} catch (Exception e) {

		}
	}
}