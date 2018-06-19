package PingUDP;
import java.io.*;
import java.net.*;

import java.io.IOException;
import java.io.InterruptedIOException;
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

	private static final int TIMEOUT = 3000;
	private static final int MAXTRIES = 5;
	
	public static void main(String args[]) throws IOException{
		
		/*
		if ((args.length <2) || (args.length >3)){
			throw new IllegalArgumentException("Parameters: Server Word Port");
			
		}
		
		InetAddress serverAddress = InetAddress.getByName(args[0]);
		byte[] bytesToSend = args[1].getBytes();
		
		int servPort = (args.length==3) ? Integer.parseInt(args[2]):7;
		*/
		
	
		InetAddress serverAddress = InetAddress.getByName("127.0.0.1"); //debugging
		int servPort = 6667;				//debugging
		String str = new String("Ping");	//debugging
		byte[] bytesToSend = str.getBytes();		//debugging
		DatagramSocket socket = new DatagramSocket();
		
		socket.setSoTimeout(TIMEOUT);
		
		DatagramPacket sendPacket = new DatagramPacket(bytesToSend, bytesToSend.length, serverAddress, servPort);
		
		DatagramPacket receivePacket = new DatagramPacket(new byte[bytesToSend.length], bytesToSend.length);
		
		int tries = 0;
		
		boolean receivedResponse = false;
		
		do {
			
			socket.send(sendPacket);
			try{
				socket.receive(receivePacket);
				if (!receivePacket.getAddress().equals(serverAddress)){
					throw new IOException("Received packet from unknown source");
					
				}
				receivedResponse=true;
				
			}
			catch(InterruptedIOException e) {
				tries +=1;
				System.out.println("Timed out, " + (MAXTRIES-tries) + "more tries...");
			}
		}
			while((!receivedResponse) && (tries<MAXTRIES) );
		
		if(receivedResponse){
			System.out.println("Received: " + new String(receivePacket.getData()));
		}
		else{
			System.out.println("No response--giving up");
		}
		
		socket.close();
	}
}
