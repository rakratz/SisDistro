package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import config.RmiConfig;

public class Servidor {

	public static void main(String[] args) {
		try {
			// Subir o RMI Registry
			LocateRegistry.createRegistry(RmiConfig.PORT);
			
			// Publicar o Serviço
			Naming.rebind(RmiConfig.SERVICE_NAME, new CalculadoraImpl());
			System.out.printf("[SERVER]: Calculadora publicada em %s%n",RmiConfig.serviceUrl());
			System.out.println("[SERVER]: Aguardando Clientes...");
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
