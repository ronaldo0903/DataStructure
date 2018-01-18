package titi.Learning.java.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest{  
    public static void main(String[] args) {  
    //�����������������͹����̡߳����ڲ�ʹ���̳߳�  
    ExecutorService exec = Executors.newCachedThreadPool();  
    //ֻ����5���߳�ͬʱ����  
    final Semaphore semp = new Semaphore(5);  
    //ģ��10���ͻ��˷���  
    for (int index = 0; index < 10; index++){  
        final int num = index;  
        Runnable run = new Runnable() {  
            public void run() {  
                try {  
                    //��ȡ���  
                    semp.acquire();  
                    System.out.println("�߳�" +   
                        Thread.currentThread().getName() + "�����ɣ�"  + num);  
                    //ģ���ʱ������  
                    for (int i = 0; i < 999999; i++) ;  
                    //�ͷ����  
                    semp.release();  
                    System.out.println("�߳�" +   
                        Thread.currentThread().getName() + "�ͷ���ɣ�"  + num);  
                    System.out.println("��ǰ�����������������" +  
                        semp.availablePermits());  
                }catch(InterruptedException e){  
                    e.printStackTrace();  
                }  
            }  
        };  
          exec.execute(run);  
    }  
    //�ر��̳߳�  
    exec.shutdown();  
    }  
} 
