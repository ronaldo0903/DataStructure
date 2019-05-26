package titi.learning.java.io;

import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) throws Exception{  
        //服务端在20006端口监听客户端请求的TCP连接  
        ServerSocket server = new ServerSocket(20006);  
        Socket client = null;  
        boolean f = true;
        int connectClients = 0;
        while(f){  
            //等待客户端的连接，如果没有获取连接  
            client = server.accept();
            connectClients ++;
            System.out.println("与第" + connectClients + "个客户端连接成功！");
            //为每个客户端连接开启一个线程  
            new Thread(new TCPServerThread(client), "TCPClient#" + connectClients).start();
            //new TCPServerThread(client).run();
        }  
        server.close();  
    }  

}
