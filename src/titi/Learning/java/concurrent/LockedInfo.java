package titi.Learning.java.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockedInfo extends Info{ // ������Ϣ��  
    private Lock lock = new ReentrantLock();    
    private Condition condition = lock.newCondition(); //����һ��Condition����  
    @Override
    public void set(String name,String content){  
        lock.lock();  
        try{  
            while(!canProduce){  
                condition.await() ;  
            }  
            this.setName(name) ;    // ��������  
            Thread.sleep(300) ;  
            this.setContent(content) ;  // ��������  
            canProduce  = false ; // �ı��־λ����ʾ����ȡ��  
            condition.signal();  
        }catch(InterruptedException e){  
            e.printStackTrace() ;  
        }finally{  
            lock.unlock();  
        }  
    }  

    public void get(){  
        lock.lock();  
        try{  
            while(canProduce){  
                condition.await() ;  
            }     
            Thread.sleep(300) ;  
            System.out.println(this.getName() +   
                " --> " + this.getContent()) ;  
            canProduce  = true ;  // �ı��־λ����ʾ��������  
            condition.signal();  
        }catch(InterruptedException e){  
            e.printStackTrace() ;  
        }finally{  
            lock.unlock();  
        }  
    }
}