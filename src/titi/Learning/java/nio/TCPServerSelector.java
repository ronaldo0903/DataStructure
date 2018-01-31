package titi.Learning.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

public class TCPServerSelector {

	//�������ĳ���  
    private static final int BUFSIZE = 256;   
    //select�����ȴ��ŵ�׼���õ��ʱ��  
    private static final int TIMEOUT = 3000;   
    public static void main(String[] args) throws IOException {  
        if (args.length < 1){  
            throw new IllegalArgumentException("Parameter(s): <Port> ...");  
        }  
        //����һ��ѡ����  
        Selector selector = Selector.open();  
        for (String arg : args){  
            //ʵ����һ���ŵ�  
            ServerSocketChannel listnChannel = ServerSocketChannel.open();  
            //�����ŵ��󶨵�ָ���˿�  
            listnChannel.socket().bind(new InetSocketAddress(Integer.parseInt(arg)));  
            //�����ŵ�Ϊ������ģʽ  
            listnChannel.configureBlocking(false);  
            //��ѡ����ע�ᵽ�����ŵ�  
            listnChannel.register(selector, SelectionKey.OP_ACCEPT);  
        }  
        //����һ��ʵ����Э��ӿڵĶ���  
        TCPProtocol protocol = new EchoSelectorProtocol(BUFSIZE);  
        //������ѯselect��������ȡ׼���õ��ŵ���������Key��  
        while (true){  
            //һֱ�ȴ�,ֱ�����ŵ�׼������I/O����  
            if (selector.select(TIMEOUT) == 0){  
                //�ڵȴ��ŵ�׼����ͬʱ��Ҳ�����첽��ִ����������  
                //����ֻ�Ǽ򵥵ش�ӡ"."  
                System.out.println("*during waiting for channels ready...*");  
                continue;  
            }  
            //��ȡ׼���õ��ŵ���������Key���ϵ�iteratorʵ��  
            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();  
            //ѭ��ȡ�ü����е�ÿ����ֵ  
            while (keyIter.hasNext()){  
                SelectionKey key = keyIter.next();   
                //���������ŵ�����Ȥ��I/O����Ϊaccept  
                if (key.isAcceptable()){  
                    protocol.handleAccept(key);  
                }  
                //����ͻ����ŵ�����Ȥ��I/O����Ϊread  
                if (key.isReadable()){  
                    protocol.handleRead(key);  
                }  
                //����ü�ֵ��Ч���������Ӧ�Ŀͻ����ŵ�����Ȥ��I/O����Ϊwrite  
                if (key.isValid() && key.isWritable()) {  
                    protocol.handleWrite(key);  
                }  
                //������Ҫ�ֶ��Ӽ������Ƴ���ǰ��key  
                keyIter.remove();   
            }  
        }  
    } 

}
