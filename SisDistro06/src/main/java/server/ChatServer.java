package server;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class ChatServer {

	public static void main(String[] args) {

		try {
			ChatServerImpl servidor = new ChatServerImpl();
			
			Registry registry = LocateRegistry.createRegistry(1099);
			
			registry.rebind("ChatService", servidor);
			
			System.out.println("Servidor de Chat RMI iniciado...");
			
			
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
