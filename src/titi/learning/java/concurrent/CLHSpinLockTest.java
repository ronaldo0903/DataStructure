package titi.learning.java.concurrent;

import java.util.concurrent.atomic.AtomicReference;

class CLHSpinLock {
    public final ThreadLocal<Node> prev;
    public final ThreadLocal<Node> node;
    public final AtomicReference<Node> tail = new AtomicReference<Node>(new Node());

    public CLHSpinLock() {
        this.node = new ThreadLocal<Node>() {
            protected Node initialValue() {
                return new Node();
            }
        };

        this.prev = new ThreadLocal<Node>() {
            protected Node initialValue() {
                return null;
            }
        };
    }

    public void lock() {
        final Node node = this.node.get();
        node.locked = true;
        Node pred = this.tail.getAndSet(node);
        this.prev.set(pred);
        while (pred.locked) {// ��������
        }
    }

    public void unlock() {
        final Node node = this.node.get();
        node.locked = false;
        this.node.set(this.prev.get());
    }

    private static class Node {
        private volatile boolean locked;
    }
}
public class CLHSpinLockTest {

	public static void main(String[] args) throws InterruptedException {
		final CLHSpinLock lock = new CLHSpinLock();
	    lock.lock();

	    for (int i = 0; i < 2; i++) {
	        new Thread(new Runnable() {
	            @Override
	            public void run() {
	                lock.lock();
	                System.out.println(Thread.currentThread().getId() + " acquired the lock!");
	                lock.unlock();
	            }
	        }).start();
	        Thread.sleep(100);
	    }

	    System.out.println("main thread unlock!");
	    lock.unlock();
	    System.out.println("Main thread doing something else here...");

	}

}
