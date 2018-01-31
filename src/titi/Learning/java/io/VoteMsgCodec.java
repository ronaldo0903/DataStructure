package titi.Learning.java.io;

import java.io.IOException;

public interface VoteMsgCodec {
	 byte[] encode(VoteMsg msg) throws IOException;  
	 VoteMsg decode(byte[] input) throws IOException;
}
