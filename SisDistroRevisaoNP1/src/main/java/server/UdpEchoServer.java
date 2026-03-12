package server;

import java.net.*;

public class UdpEchoServer {

	 private static final int MAXMSG = 255;
	 private static final int PORT = 8000;

	public static void main(String[] args) throws Exception{
		
		DatagramSocket socket = new DatagramSocket(PORT);

        byte[] buffer = new byte[MAXMSG];

        System.out.println("UDP Echo Server running on port " + PORT);
        
        while (true) {
        	 DatagramPacket request =
                     new DatagramPacket(buffer, buffer.length);
        	 
        	 socket.receive(request);
        	 
        	 String message = new String(
                     request.getData(),
                     0,
                     request.getLength() );
        	 
        	 System.out.println("Received: " + message);
        	 
        	 DatagramPacket reply =
                     new DatagramPacket(
                             request.getData(),
                             request.getLength(),
                             request.getAddress(),
                             request.getPort() );
        	 
        	 socket.send(reply);

        }
        // socket.close();
	}

}
