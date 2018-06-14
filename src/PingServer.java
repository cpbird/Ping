import java.net.*;
import java.io.*;

public class PingServer {
	
	private static final int BUFFERSIZE = 32;
	
	public static void main(String[] args) throws IOException{
		
	/* if(args.length!=1)
			throw new IllegalArgumentException("parameter:port");
		
		int serverPort = Integer.parseInt(args[0]); */
		
		
		//ServerSocket serverSocket = new ServerSocket(serverPort); 
		
		ServerSocket serverSocket = new ServerSocket(6667); //debugging
		int recvMsgSize;
		byte[] receiveBuffer = new byte [BUFFERSIZE];
		
		while (true){
			Socket clientSocket = serverSocket.accept();
			SocketAddress clientAddress = clientSocket.getRemoteSocketAddress();
			System.out.println("Client at : "+ clientAddress);
	
			
			
			InputStream in = clientSocket.getInputStream();
			OutputStream out = clientSocket.getOutputStream();
			
			while((recvMsgSize = in.read(receiveBuffer))!= -1){
				out.write(receiveBuffer, 0, recvMsgSize);
				
			}
			clientSocket.close();
		}
	}
}

	


