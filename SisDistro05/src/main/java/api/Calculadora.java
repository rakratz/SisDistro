package api;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculadora extends Remote {
	int soma(int a, int b) throws RemoteException;
	int subtrai(int a, int b) throws RemoteException;
	int multiplica(int a, int b) throws RemoteException;
	int divide(int a, int b) throws RemoteException;
}
