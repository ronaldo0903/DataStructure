package titi.Learning.java.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class TCPServerThread implements Runnable{
	private Socket client = null;  
    public TCPServerThread(Socket client){  
        this.client = client;  
    }
    
  //����ͨ��ϸ�ڵľ�̬������������Ҫ�Ƿ����̳߳ط������ĵ���  
    public static void execute(Socket client){  
        try{  
            //��ȡSocket���������������ͻ��˷�������    
            PrintStream out = new PrintStream(client.getOutputStream());  
            //��ȡSocket�����������������մӿͻ��˷��͹���������  
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));  
            boolean flag =true;  
            while(flag){  
                //���մӿͻ��˷��͹���������    
                String str =  buf.readLine();  
                if(str == null || "".equals(str)){
                	out.println("Client sent empty string!");  
                    flag = false;  
                }else{  
                    if("bye".equals(str)){  
                        flag = false;  
                    }else{  
                        //�����յ����ַ���ǰ�����echo�����͵���Ӧ�Ŀͻ���    
                        out.println("echo:" + str);  
                    }  
                }  
            }  
            out.close();  
            buf.close();  
            client.close();  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    }  
    @Override  
    public void run() {  
        execute(client);  
    } 
}
