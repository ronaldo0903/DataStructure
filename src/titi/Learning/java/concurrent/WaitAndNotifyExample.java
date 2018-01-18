package titi.Learning.java.concurrent;

/*
 * 使用2个线程交替往终端输出1->2->1->2->1->2...的序列
 */
public class WaitAndNotifyExample implements Runnable {

	private int num;
    private Object lock;
    
    public WaitAndNotifyExample(int num, Object lock) {
        super();
        this.num = num;
        this.lock = lock;
    }

    public void run() {
        try {
            while(true){
                synchronized(lock){
                    lock.notifyAll();
                    lock.wait();
                    System.out.println(num);
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public static void main(String[] args){
        final Object lock = new Object();
        
        Thread thread1 = new Thread(new WaitAndNotifyExample(1,lock));
        Thread thread2 = new Thread(new WaitAndNotifyExample(2, lock));
        
        thread1.start();
        thread2.start();
    }

}
