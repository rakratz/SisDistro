package client;


import api.Calculadora;
import config.RmiConfig;

import java.rmi.Naming;

/**
 * Cliente RMI.
 *
 * Faz o lookup no Registry para obter o "stub" (proxy) do serviço
 * remoto e invoca os métodos como se fossem locais.
 *
 * Experimente abrir vários clientes em paralelo: o RMI cria threads
 * no servidor e atende todos simultaneamente.
 */
public class Cliente {
    public static void main(String[] args) {
        try {
            // Obtém o stub remoto
            Calculadora calc = (Calculadora) Naming.lookup(RmiConfig.serviceUrl());

            long t0 = System.currentTimeMillis();

            System.out.println("2 + 3 = " + calc.soma(2, 3));
            System.out.println("10 - 4 = " + calc.subtrai(10, 4));
            System.out.println("6 * 7 = " + calc.multiplica(6, 7));
            System.out.println("20 / 5 = " + calc.divide(20, 5));

            long tf = System.currentTimeMillis();
            System.out.printf("Tempo total (com delays do servidor): %.2fs%n",
                    (tf - t0) / 1000.0);
        } catch (Exception e) {
            System.err.println("[CLIENT] Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

