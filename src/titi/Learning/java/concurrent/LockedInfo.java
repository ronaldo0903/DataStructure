package titi.Learning.java.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockedInfo extends Info{ // 定义信息类  
    private Lock lock = new ReentrantLock();    
    private Condition condition = lock.newCondition(); //产生一个Condition对象  
    @Override
    public void set(String name,String content){  
        lock.lock();  
        try{  
            while(!canProduce){  
                condition.await() ;  
            }  
            this.setName(name) ;    // 设置名称  
            Thread.sleep(300) ;  
            this.setContent(content) ;  // 设置内容  
            canProduce  = false ; // 改变标志位，表示可以取走  
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
            canProduce  = true ;  // 改变标志位，表示可以生产  
            condition.signal();  
        }catch(InterruptedException e){  
            e.printStackTrace() ;  
        }finally{  
            lock.unlock();  
        }  
    }
}