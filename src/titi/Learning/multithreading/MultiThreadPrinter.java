package titi.Learning.multithreading;

/*
实现3个线程交替打印1，2，3，4,...,99
 */
public class MultiThreadPrinter {
    public static class MyPrinter implements Runnable{
        private Object prev;
        private Object self;
        private int initial;
        public MyPrinter(Object prev, Object self, int initialValue) {
            this.prev = prev;
            this.self = self;
            this.initial = initialValue;

        }
        public void run() {
            int current = initial;
            while(current <= 99) {
                synchronized(prev) {
                    synchronized (self) {
                        System.out.println(current + " is printed by thread " + Thread.currentThread().getName());
                        self.notify();
                    }
                    try {
                        if(current <= 96 ) prev.wait();
                    } catch (Exception e) {

                    }
                }
                current += 3;
            }

        }
    }

    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        MyPrinter PA = new MyPrinter(c, a, 1);
        MyPrinter PB = new MyPrinter( a, b, 2);
        MyPrinter PC = new MyPrinter( b, c, 3);
        Thread TA = new Thread(PA, "A");
        Thread TB = new Thread(PB, "B");
        Thread TC = new Thread(PC, "C");
        try {
            TA.start();
            Thread.sleep(100);
            TB.start();
            Thread.sleep(100);
            TC.start();
            Thread.sleep(100);
            TA.join();
            TB.join();
            TC.join();

        } catch (Exception e) {

        }
    }
}
