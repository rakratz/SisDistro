package app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.Pessoa;

public class Servidor {

	public static void main(String[] args) {
		int porta = 12345;
		
		try(ServerSocket serverSocket = new ServerSocket(porta)){
			 System.out.println("Servidor aguardando conexão na porta " + porta);
			 
			 Socket socket = serverSocket.accept();
			 System.out.println("Cliente Conectado");
			 
			 ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			 Pessoa p2 = (Pessoa) ois.readObject();
			 System.out.println("Desserilizado: " + p2);
			 ois.close();
			 socket.close();
			 
		}
		catch(IOException | ClassNotFoundException e) {
			System.out.println("Erro ao desserializar: " + e.getMessage());
		}

	}

}
