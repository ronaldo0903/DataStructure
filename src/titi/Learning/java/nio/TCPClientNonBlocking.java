package titi.Learning.java.nio;

import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TCPClientNonBlocking {

	public static void main(String[] args) throws Exception {
		if ((args.length < 2) || (args.length > 3))   
	        throw new IllegalArgumentException("��������ȷ");  
	        //��һ��������ΪҪ���ӵķ���˵���������IP  
	        String server = args[0];   
	        //�ڶ�������ΪҪ���͵�����˵��ַ���  
	        byte[] argument = args[1].getBytes();  
	        //����е���������������Ϊ�˿ںţ����û�У���˿ں���Ϊ7  
	        int servPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;  
	        //����һ���ŵ�������Ϊ������ģʽ  
	        SocketChannel clntChan = SocketChannel.open();  
	        clntChan.configureBlocking(false);  
	        //�����˷�������  
	        if (!clntChan.connect(new InetSocketAddress(server, servPort))){  
	            //���ϵ���ѯ����״̬��ֱ���������  
	            while (!clntChan.finishConnect()){  
	                //�ڵȴ����ӵ�ʱ�������ִ�����������Գ�ַ��ӷ�����IO���첽����  
	                //����Ϊ����ʾ�÷�����ʹ�ã�ֻ��һֱ��ӡ"."  
	                System.out.print(".");    
	            }  
	        }  
	        //Ϊ��������ӡ��"."������������������з�  
	        System.out.print("\n");  
	        //�ֱ�ʵ����������д�Ļ�����  
	        ByteBuffer writeBuf = ByteBuffer.wrap(argument);  
	        ByteBuffer readBuf = ByteBuffer.allocate(argument.length);  
	        //���յ����ܵ��ֽ���  
	        int totalBytesRcvd = 0;   
	        //ÿһ�ε���read�����������յ����ֽ���  
	        int bytesRcvd;
	        StringBuffer sb = new StringBuffer();
	        //ѭ��ִ�У�ֱ�����յ����ֽ����뷢�͵��ַ������ֽ������  
	        while (totalBytesRcvd < argument.length){  
	            //���������ͨ����д���ݵĻ������л���ʣ����ֽڣ������������д���ŵ�  
	            if (writeBuf.hasRemaining()){  
	                clntChan.write(writeBuf);  
	            }  
	            //���read�������յ�-1����������˹رգ��׳��쳣  
	            if ((bytesRcvd = clntChan.read(readBuf)) == -1){  
	                throw new SocketException("Connection closed prematurely");  
	            }  
	            //������յ������ֽ���  
	            totalBytesRcvd += bytesRcvd;  
	            //�ڵȴ�ͨ����ɵĹ����У��������ִ���������������ַ�����IO���첽����  
	            //����Ϊ����ʾ�÷�����ʹ�ã�ͬ��ֻ��һֱ��ӡ"."  
	            System.out.println("*during buffer reading...*");
	        }  
	        //��ӡ�����յ�������  
	        System.out.println("Received: " +  new String(readBuf.array(), 0, totalBytesRcvd));
	        //�ر��ŵ�  
	        clntChan.close(); 

	}

}
