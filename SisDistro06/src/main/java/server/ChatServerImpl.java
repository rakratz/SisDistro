package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import client.ChatClientInterface;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServerInterface{
	
	private List<ChatClientInterface> clientes;
	
	protected ChatServerImpl()  throws RemoteException {
		clientes = new ArrayList<>();
	}
	
	@Override
	public synchronized void registrarCliente(ChatClientInterface cliente) throws RemoteException {
		clientes.add(cliente);
		
		System.out.println("Novo cliente adcionado...");
	}
	
	@Override
	public synchronized void enviarMensagem(String nome, String mensagem) throws RemoteException {
		String msg = nome + ": " + mensagem;
		for (ChatClientInterface cliente : clientes) {
			new Thread(() -> {
                try {
                    cliente.receberMensagem(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
		}
	}
	
}
