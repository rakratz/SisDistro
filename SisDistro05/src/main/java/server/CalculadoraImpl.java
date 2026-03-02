package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import api.Calculadora;

public class CalculadoraImpl extends UnicastRemoteObject implements Calculadora{	

	public CalculadoraImpl() throws RemoteException { 
		super();
	}
	
	@Override
	public int soma(int a, int b) throws RemoteException {
		simulaProcessamento();
		return a + b;
	}

	@Override
	public int subtrai(int a, int b) throws RemoteException{
		simulaProcessamento();
		return a - b;
	}
	@Override
	public int multiplica(int a, int b) throws RemoteException{
		simulaProcessamento();
		return a * b;
	}
	@Override
	public int divide(int a, int b) throws RemoteException {
		simulaProcessamento();
		if (b==0) throw new RemoteException("Divisão por Zero");
		return a / b;
	}
	
	private void simulaProcessamento() {
		System.out.println("[SERVER]: Processando...");
		try {
			Thread.sleep(3000); // simula 3s de processamento
		} catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
