package titi.learning.java.thread;

import java.util.concurrent.TimeUnit;
/*
1. Java中所谓的父子进程间并没有生命周期上的绑定关系，父进程结束后子进程可以继续执行；
2. 守护进程会在所有用户线程都执行完毕后自动终止；
3. Java的主线程执行结束并不意味着JVM进程退出，其它线程还可能在继续运行；

 */
public class ThreadLifeCycleTest {
    public static void main(String[] args)
    {
        Thread mainThread = new Thread(new Runnable(){
            @Override
            public void run()
            {
                Thread childThread = new Thread(new ChildThread(), "My Child Thread");
                childThread.setDaemon(true);
                childThread.start();
                System.out.println("I'm main thread...");
            }
        }, "My Main Thread");
        mainThread.start();

        Thread otherThread = new Thread(new Runnable(){
            @Override
            public void run()
            {
                while(true)
                {
                    System.out.println("I'm other user thread...");
                    try
                    {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }, "Other Thread");
        otherThread.start();
        try {
            //Thread.currentThread().stop();
            //Thread.sleep(10000);
            mainThread.join(5000);
            System.out.println(mainThread.hashCode());
            otherThread.join(5000);
            System.out.println(otherThread.hashCode());
            System.out.println("Java Main thread exits now!");
            //Thread.currentThread().stop();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

class ChildThread implements Runnable
{
    @Override
    public void run()
    {
        while(true)
        {
            System.out.println("I'm child thread..");
            try
            {
                TimeUnit.MILLISECONDS.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
