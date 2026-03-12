package cliente;

import java.io.*;
import java.net.*;

public class TcpCliente {

	public static void main(String[] args) throws Exception {
		String sentence;
        String modifiedSentence;
        
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));
       
        Socket clientSocket = new Socket("localhost", 2000);

        DataOutputStream outToServer =
                new DataOutputStream(clientSocket.getOutputStream());
        
        BufferedReader inFromServer =
                new BufferedReader(new InputStreamReader(
                		clientSocket.getInputStream()));
        
        System.out.print("Digite uma frase: ");
        sentence = inFromUser.readLine();
        
        outToServer.writeBytes(sentence + '\n');
        
        modifiedSentence = inFromServer.readLine();
        
        System.out.println("Recebido do servidor: " + modifiedSentence);
        
        clientSocket.close();

        
        
        
	}

}
