package PingUDP;
import java.io.*;
import java.net.*;

public class ClientUDP {
	
	private static final int TIMEOUT = 3000;
	private static final int MAXTRIES = 5;
	
	public static void main(String args[]) throws IOException {
		
		String str = "Hello";
		byte[] bytesToSend = str.getBytes();
		InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
		DatagramSocket socket = new DatagramSocket(); 
		socket.setSoTimeout(TIMEOUT);
		
		DatagramPacket sendPacket = new DatagramPacket(bytesToSend, bytesToSend.length,serverAddress , 6668);
		
		DatagramPacket receivePacket = new DatagramPacket(new byte[bytesToSend.length], bytesToSend.length);
		int tries = 0;
		boolean receivedResponse = false;
		
		do{
			socket.send(sendPacket);
			
			try{
				socket.receive(receivePacket);
				
				if(!receivePacket.getAddress().equals(serverAddress)){
					throw new IOException("Received packet from unknown source");
				}
				receivedResponse=true;
				}
			catch(InterruptedIOException e){
				tries+=1;
				System.out.println("Timed out, " + (MAXTRIES-tries) + " more tries...");
			}
		}
		while ((!receivedResponse) && (tries<MAXTRIES));
		
		if(receivedResponse){
			System.out.println("Received: " + new String(receivePacket.getData()));
		}
		else {
			System.out.println("No response---giving up");
		}
		socket.close();
	}

}
