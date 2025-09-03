package server;

import config.RmiConfig;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Servidor {
    public static void main(String[] args) {
        try {
        	 // 1) Sobe o Registry
            LocateRegistry.createRegistry(RmiConfig.PORT); // sobe o Registry (1099)
            
            // 2) Cria a implementação do serviço e Publica com nome lógico
            Naming.rebind(RmiConfig.SERVICE_NAME, new CalculadoraImpl());
            
            System.out.printf("[SERVER] Serviço publicado em %s%n", RmiConfig.serviceUrl());
            System.out.println("[SERVER] Aguardando clientes...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
