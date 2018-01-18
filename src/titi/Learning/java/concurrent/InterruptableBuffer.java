package titi.Learning.java.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class InterruptableBuffer {  

    private ReentrantLock lock = new ReentrantLock();  

    public void write() {  
        lock.lock();  
        try {  
            long startTime = System.currentTimeMillis();  
            System.out.println("��ʼ�����buffд�����ݡ�");  
            for (;;)// ģ��Ҫ����ܳ�ʱ��      
            {  
                if (System.currentTimeMillis()  
                        - startTime > Integer.MAX_VALUE) {  
                    break;  
                }  
            }  
            System.out.println("����д����");  
        } finally {  
            lock.unlock();  
        }  
    }  

    public void read() throws InterruptedException {  
        lock.lockInterruptibly();// ע�����������Ӧ�ж�      
        try {  
            System.out.println("�����buff������");  
        } finally {  
            lock.unlock();  
        }  
    }  

    public static void main(String args[]) {  
        InterruptableBuffer buff = new InterruptableBuffer();  

        final Writer2 writer = new Writer2(buff);  
        final Reader2 reader = new Reader2(buff);  

        writer.start();  
        reader.start();  

        new Thread(new Runnable() {  

            @Override  
            public void run() {  
                long start = System.currentTimeMillis();  
                for (;;) {  
                    if (System.currentTimeMillis()  
                            - start > 5000) {  
                        System.out.println("�����ˣ������ж�");  
                        reader.interrupt();  //�˴��ж϶�����  
                        break;  
                    }  
                }  
            }  
        }).start();  

    }  
}  

class Reader2 extends Thread {  

    private InterruptableBuffer buff;  

    public Reader2(InterruptableBuffer buff) {  
        this.buff = buff;  
    }  

    @Override  
    public void run() {  

        try {  
            buff.read();//�����յ��жϵ��쳣���Ӷ���Ч�˳�      
        } catch (InterruptedException e) {  
            System.out.println("�Ҳ�����");  
        }  

        System.out.println("������");  

    }  
}  

class Writer2 extends Thread {  

    private InterruptableBuffer buff;  

    public Writer2(InterruptableBuffer buff) {  
        this.buff = buff;  
    }  

    @Override  
    public void run() {  
        buff.write();  
    }  

} 
