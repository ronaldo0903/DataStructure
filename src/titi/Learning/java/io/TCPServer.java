package titi.Learning.java.io;

import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) throws Exception{  
        //�������20006�˿ڼ����ͻ��������TCP����  
        ServerSocket server = new ServerSocket(20006);  
        Socket client = null;  
        boolean f = true;  
        while(f){  
            //�ȴ��ͻ��˵����ӣ����û�л�ȡ����  
            client = server.accept();  
            System.out.println("��ͻ������ӳɹ���");  
            //Ϊÿ���ͻ������ӿ���һ���߳�  
            //new Thread(new TCPServerThread(client)).start();
            new TCPServerThread(client).run();
        }  
        server.close();  
    }  

}
