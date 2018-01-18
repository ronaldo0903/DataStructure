package titi.Learning.java.concurrent;

public class NonInterruptableBuffer {

	private Object lock;  

    public NonInterruptableBuffer() {  
        lock = this;  
    }  

    public void write() {  
        synchronized (lock) {  
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
        }  
    }  

    public void read() {  
        synchronized (lock) {  
            System.out.println("�����buff������");  
        }  
    }  

    public static void main(String[] args) {  
    	NonInterruptableBuffer buff = new NonInterruptableBuffer();  

        final Writer writer = new Writer(buff);  
        final Reader reader = new Reader(buff);  

        writer.start();  
        reader.start();  

        new Thread(new Runnable() {  

            @Override  
            public void run() {  
                long start = System.currentTimeMillis();  
                for (;;) {  
                    //��5����ȥ�ж϶�      
                    if (System.currentTimeMillis()  
                            - start > 5000) {  
                        System.out.println("�����ˣ������ж�");  
                        reader.interrupt();  //�����ж϶��߳�  
                        break;  
                    }  

                }  

            }  
        }).start();  
        // �����ڴ�����������߳����˳��ȴ�������������ԸΥ��һ��������̷߳����Լ��ò�������  
        // ��һֱ��ʼ�ȴ��ˣ�������������Ҳ�ò���������Ϊд�߳�Ҫ21���������� T_T ����ʹ�����ж�����  
        // ����������Ӧ�£��������Ҫ�����ˡ����ʱ��ReentrantLock����һ�ֻ�������������Ӧ�жϣ�  
        // �á����������������¸ҷ�����������ĵȴ�����������дBuffer����࣬�ͽ�BufferInterruptibly�ɣ����жϻ��档  
    } 

}
class Writer extends Thread {  

    private NonInterruptableBuffer buff;  

    public Writer(NonInterruptableBuffer buff) {  
        this.buff = buff;  
    }  

    @Override  
    public void run() {  
        buff.write();  
    }  
}  

class Reader extends Thread {  

    private NonInterruptableBuffer buff;  

    public Reader(NonInterruptableBuffer buff) {  
        this.buff = buff;  
    }  

    @Override  
    public void run() {  

        buff.read();//������ƻ�һֱ����      

        System.out.println("������");  

    }  
}
