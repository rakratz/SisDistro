package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClientInterface extends Remote{
	void receberMensagem(String mensagem) throws RemoteException;

}
