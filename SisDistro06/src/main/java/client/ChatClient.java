package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import server.ChatServerImpl;
import server.ChatServerInterface;
import client.*;

public class ChatClient extends UnicastRemoteObject implements ChatClientInterface{
	
	private ChatServerInterface server;
	private String nome;
	
	protected ChatClient(String nome) throws Exception{
		this.nome = nome;
	}
	
	@Override
	public void receberMensagem(String mensagem) throws RemoteException{
		System.out.println(mensagem);
	}
	
	public void iniciarChat() throws Exception{
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String msg = scanner.nextLine();
			
			server.enviarMensagem(nome, msg);
		}
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Informe seu nome: ");
		String nome = scanner.nextLine();
		
		ChatClient client;
		try {
			client = new ChatClient(nome);
			client.iniciarChat();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
	}

}
