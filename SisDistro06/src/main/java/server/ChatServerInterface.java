package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.ChatClientInterface;

// Stub <interface> do ChatServer
public interface ChatServerInterface extends Remote {
	void registrarCliente(ChatClientInterface cliente) throws RemoteException;
	void enviarMensagem(String nome, String mensagem) throws RemoteException;
}
