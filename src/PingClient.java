import java.net.Socket;
import java.net.SocketException;
import java.io.*;


public class PingClient {

	public static void main(String args[]) throws IOException{
		
		/* if ((args.length<2) || (args.length>3)) 
			throw new IllegalArgumentException("Enter server, word, port");
			
			String server = args[0];
			
			byte[] data = args[1].getBytes();
			
			int serverPort = (args.length==3) ? Integer.parseInt(args[2]):7;
			*/
		
			String server = "127.0.0.1";		//debugging
			int serverPort= 6667;				//debugging
			String str = new String("Ping");	//debugging
			byte[] data = str.getBytes();		//debugging
			
			
			
			Socket socket = new Socket(server, serverPort);
			System.out.println("Connected");
			
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			
			out.write(data);
			
			int totalBytesRcvd = 0;
			int bytesRcvd;
			while (totalBytesRcvd < data.length){
				if((bytesRcvd=in.read(data, totalBytesRcvd, data.length - totalBytesRcvd))== -1)
					throw new SocketException("Connection closed early");
				totalBytesRcvd += bytesRcvd;
			}
			
			System.out.println("Received: " + new String(data));
			socket.close();
			
		}
	}

