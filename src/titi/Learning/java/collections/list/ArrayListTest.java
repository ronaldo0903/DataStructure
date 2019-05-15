package titi.Learning.java.collections.list;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
/*
 * 测试ArrayList和CopyOnWriteArrayList在多线程并发读取时的行为
 * 测试结果：
 * 1. ArrayList
 *    1) 不可以使用普通的for循环，一边遍历，一边往ArrayList里添加/删除数据，会抛出ConcurrentModificationException
 *    2) 如果需要实现一边遍历，一边删除的功能，使用iterator
 *    3) 当线程A在ArrayList的迭代过程中，线程B执行了增加/删除元素的操作，这时候A会直接fail，抛出ConcurrentModificationException
 * 2. CopyOnWriteArrayList
 *    1) 迭代器线程基于迭代器初始化时刻T看到的快照来遍历，其它线程在T时刻之后对list的更改对迭代过程不可见；
 *    2) 可以使用普通的for循环，一边遍历，一边往CopyOnWriteArrayList里添加/删除数据

 */

public class ArrayListTest {
    public static void main(String[] args) {
        System.out.println("Test ArrayList");
        List<Integer> intList = new CopyOnWriteArrayList<>();
        //List<Integer> intList = new ArrayList<>();
        intList.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Object monitor = new Object();
        Thread iterThd = new Thread("Iterating thread") {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    for (Integer i : intList) {
                        synchronized (monitor) {
                            System.out.println("In iterating thread, i = " + i);
                            monitor.wait(1000);
                        }
                    }
                } catch (InterruptedException e) {
                }
            }

        };
        Thread operThd = new Thread("Operating Thread") {
            @Override
            public void run() {
                for (Integer i : intList) {
                    try {
                        Thread.sleep(500);
                        int r = new Random().nextInt(intList.size() - 1);
                        /*if(intList.size() > 5) {
                            intList.remove(r);
                        }*/
                        synchronized (monitor) {
                            System.out.println("oper thread gets the lock and add 99 at index:" + r);
                            intList.add(r, 99);
                            monitor.notify();
                        }

                    } catch (InterruptedException e) {

                    }
                }
                /*for(int i=0; i<3; i++) {
                    try {
                        Thread.sleep(300);
                        int r = new Random().nextInt(intList.size() - 1);
                        synchronized (monitor) {
                            System.out.println("oper thread gets the lock and add 99 at index:" + r);
                            intList.add(r, 99);
                            monitor.notify();
                        }
                        //intList.remove(r);
                    } catch (InterruptedException e) {

                    }
                }*/
                /*Iterator iter = intList.iterator();
                while (iter.hasNext()) {
                    try {
                        Thread.sleep(500);
                        int r = new Random().nextInt(intList.size() -1);
                        System.out.println("Iterating usig iterator, current element is " + iter.next());
                        iter.remove();
                    }
                    catch (InterruptedException e) {

                    }
                }*/
            }
        };
        iterThd.start();
        operThd.start();
        try {
            iterThd.join();
            operThd.join();
        } catch (Exception e) {

        }
    }
}
