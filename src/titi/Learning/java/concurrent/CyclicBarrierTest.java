package titi.Learning.java.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {   
    public static void main(String[] args) {   
            //����CyclicBarrier����  
            //������ִ����һ��5���̵߳Ĳ����������ִ��MainTask����  
            CyclicBarrier cb = new CyclicBarrier(5, new MainTask());   
            new SubTask("A", cb).start();   
            new SubTask("B", cb).start();   
            new SubTask("C", cb).start();   
            new SubTask("D", cb).start();   
            new SubTask("E", cb).start();  
    }   
}   

/**  
* ���ִ�е����� 
*/   
class MainTask implements Runnable {   
    public void run() {   
            System.out.println("......����Ҫִ������������......");   
    }   
}   

/**  
* һ�鲢������  
*/   
class SubTask extends Thread {   
    private String name;   
    private CyclicBarrier cb;   

    SubTask(String name, CyclicBarrier cb) {   
            this.name = name;   
            this.cb = cb;   
    }   

    public void run() {   
            System.out.println("[��������" + name + "]  ��ʼִ��");   
            for (int i = 0; i < 999999; i++) ;    //ģ���ʱ������   
            System.out.println("[��������" + name + "]  ��ʼִ����ϣ�֪ͨ�ϰ���");   
            try {   
                    //ÿִ����һ�������֪ͨ�ϰ���   
                    cb.await();   
            } catch (InterruptedException e) {   
                    e.printStackTrace();   
            } catch (BrokenBarrierException e) {   
                    e.printStackTrace();   
            }   
    }   
}  
