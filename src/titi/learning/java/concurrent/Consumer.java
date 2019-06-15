package titi.learning.java.concurrent;

import java.util.concurrent.CyclicBarrier;

class Consumer implements Runnable {
	private Info info = null;
	private CyclicBarrier barrier;

	public Consumer(Info info) {
		this.info = info;
	}

	public Consumer(Info info, CyclicBarrier b) {
		this.info = info;
		this.barrier = b;
	}

	public void run() {
		try {
			barrier.await();
			for (int i = 0; i < 10; i++) {
				this.info.get();
			}
		} catch (Exception e) {

		}
	}
}