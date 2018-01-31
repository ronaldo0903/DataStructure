package titi.Learning.java.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;

public interface TCPProtocol {
	//accept I/O��ʽ  
    void handleAccept(SelectionKey key) throws IOException;  
    //read I/O��ʽ  
    void handleRead(SelectionKey key) throws IOException;  
    //write I/O��ʽ  
    void handleWrite(SelectionKey key) throws IOException; 
}
