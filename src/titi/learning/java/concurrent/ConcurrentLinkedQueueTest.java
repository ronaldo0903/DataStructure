package titi.learning.java.concurrent;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
/*
当有多线程正在操作ConcurrentLinkedQueue时，使用foreach遍历时不能保证获取到Queue的所有数据，因为遍历到next=null的节点时遍历
就会结束
 */
public class ConcurrentLinkedQueueTest {
    static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue(Arrays.asList(1, 2, 3, 4, 5, 6));
    static Runnable r = new Runnable() {
        public void run() {
            /*for (int e : queue) {
                System.out.println("In " + Thread.currentThread().getName() + ", element is : " + e);
            }*/
            Iterator iterator = queue.iterator();
            while(iterator.hasNext()) {
                System.out.println("In " + Thread.currentThread().getName() + ", element is : " + iterator.next());
            }
        }
    };
    public static void main(String[] args) {
        Thread itA = new Thread(r, "1st itering thread");
        Thread itB = new Thread(r, "2nd itering thread");

        Thread tA = new Thread(new Runnable() {
            public void run() {
                for (int i = 100; i < 200; i++) {
                    try {
                        Thread.sleep(50 + new Random().nextInt(50));
                    } catch (Exception e) {

                    }
                    queue.add(i);
                }
            }
        });

        Thread tB = new Thread(new Runnable() {
            public void run() {
                for (int i = 500; i < 600; i++) {
                    try {
                        Thread.sleep(50 + new Random().nextInt(50));
                        queue.add(i);
                    } catch (Exception e) {

                    }
                }
            }
        });
        tA.start();
        tB.start();
        try {
            Thread.sleep(1500);
            itA.start();
            Thread.sleep(1500);
            itB.start();
        } catch (Exception e) {

        }

    }
}
