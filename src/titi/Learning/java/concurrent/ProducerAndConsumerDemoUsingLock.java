package titi.Learning.java.concurrent;


public class ProducerAndConsumerDemoUsingLock{  
    public static void main(String args[]){  
        LockedInfo info = new LockedInfo(); // ʵ����Info����  
        Producer pro = new Producer(info) ; // ������  
        Consumer con = new Consumer(info) ; // ������  
        new Thread(pro).start() ;  
        //�������������̺߳��������������߳�  
        try{  
            Thread.sleep(500) ;  
        }catch(InterruptedException e){  
            e.printStackTrace() ;  
        }  

        new Thread(con).start() ;  
    }  
}  
