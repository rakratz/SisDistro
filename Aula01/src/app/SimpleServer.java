package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

	public static void main(String[] args) throws IOException{
		int port = 4000;
		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("Servidor iniciado na porta "+port);
		Socket clienteSocket = serverSocket.accept();
		System.out.println("Cliente Conectou..");
		System.out.println("IP do cliente: "+clienteSocket.getInetAddress());
		System.out.println("Host do cliente: "+clienteSocket.getInetAddress().getHostName());
		
		InputStreamReader inputStreamReader = new InputStreamReader(clienteSocket.getInputStream());
		BufferedReader reader = new BufferedReader(inputStreamReader);
		
		String msg;
		while ( (msg = reader.readLine() ) != null) {
			System.out.println("O cliente falou :" + msg);
		}
		clienteSocket.close();
		serverSocket.close();
		System.out.println("Conexão fechada!");
	}

}
