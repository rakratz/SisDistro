package server;

import java.io.*;
import java.net.*;


public class TcpServer {

	public static void main(String[] args) throws Exception{

		String inFromClient;
        String outToClient;
        
     // socket de "boas-vindas"
        ServerSocket welcomeSocket = new ServerSocket(2000);
        
        while (true) {
        	 // socket de conexão TCP
            Socket sock = welcomeSocket.accept();
            
            // buffer de entrada que recebe um stream
            BufferedReader socketIn =
                    new BufferedReader(
                            new InputStreamReader(
                                    sock.getInputStream()
                            ));
            inFromClient = socketIn.readLine();
            outToClient = inFromClient.toUpperCase() + '\n';
            // stream de saída
            DataOutputStream socketOut =
                    new DataOutputStream(sock.getOutputStream());
            
            // escrevendo no socket
            socketOut.writeBytes(outToClient);
            
            sock.close();
        }
	}

}
