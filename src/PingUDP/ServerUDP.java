package PingUDP;
import java.io.*;
import java.net.*;

public class ServerUDP {

	private static final int ECHOMAX = 255;
	
	public static void main(String args[]) throws IOException{
		
		int servPort = 6669;
		
		DatagramSocket socket = new DatagramSocket(servPort);
		DatagramPacket packet = new DatagramPacket(new byte[ECHOMAX], ECHOMAX);
		
		while(true){
			socket.receive(packet);
			System.out.println("Handling client at: "+ packet.getAddress().getHostAddress()+ " on port " +
																								packet.getPort());
			
			socket.send(packet);
			packet.setLength(ECHOMAX);
		}
			
		}
	}

	

