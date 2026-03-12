package cliente;

import java.net.*;
import java.util.*;

public class UdpEchoClient {

	 private static final int MAXMSG = 255;
	 private static final int PORT = 8000;
	 private static final String SERVER = "localhost";
	
	public static void main(String[] args) throws Exception {
		 	//if (args.length != 1) {
	        //    System.err.println("usage: java UDPEchoClient servidor");
	        //    System.exit(0);
	        //}

	        // InetAddress serverAddress = InetAddress.getByName(args[0]);
			InetAddress serverAddress = InetAddress.getByName(SERVER);
	        DatagramSocket socket = new DatagramSocket();

	        Scanner in = new Scanner(System.in);

	        for (;;) {   // loop infinito

	            System.out.printf("Type a message: ");
	            String msg = in.nextLine();

	            byte[] msgData = msg.getBytes();

	            DatagramPacket request =
	                    new DatagramPacket(msgData,
	                                       msgData.length,
	                                       serverAddress,
	                                       PORT);

	            socket.send(request);

	            byte[] buffer = new byte[MAXMSG];

	            DatagramPacket reply =
	                    new DatagramPacket(buffer, MAXMSG);

	            socket.receive(reply);

	            String answer = new String(
	                    reply.getData(),
	                    0,
	                    reply.getLength()
	            );

	            System.out.printf("The answer is: \"%s\"\n", answer);
	        }
	        // socket.close();  // nunca executado devido ao loop infinito
	        // in.close();  // nunca executado devido ao loop infinito
	}

}
