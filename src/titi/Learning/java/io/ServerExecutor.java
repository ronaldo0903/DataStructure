package titi.Learning.java.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ServerExecutor {
	public static void main(String[] args) throws IOException{  
        //�������20006�˿ڼ����ͻ��������TCP����   
        ServerSocket server = new ServerSocket(20006);  
        Socket client = null;  
        //ͨ������Executors��ľ�̬����������һ��ExecutorServiceʵ��  
        //ExecutorService�ӿ���Executor�ӿڵ��ӽӿ�  
        Executor service = Executors.newCachedThreadPool();  
        boolean f = true;  
        while(f){  
            //�ȴ��ͻ��˵�����  
            client = server.accept();  
            System.out.println("��ͻ������ӳɹ���");  
            //����execute()����ʱ�������Ҫ���ᴴ��һ���µ��߳����������񣬵������Ȼ᳢��ʹ�����е��̣߳�  
            //���һ���߳̿���60�����ϣ������Ƴ��̳߳أ�  
            //���⣬��������Executor���ڲ��Ŷӣ����������������Ŷ�  
            service.execute(new TCPServerThread(client));  
        }   
        server.close();  
    } 
}
