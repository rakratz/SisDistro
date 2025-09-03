package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import api.Calculadora;

/**
* Implementação do serviço remoto.
*
* Estende UnicastRemoteObject para que o objeto seja exportado
* como remoto automaticamente (TCP, serialização, etc.).
*
* IMPORTANTE: esta classe é stateless, logo suporta múltiplos
* clientes simultaneamente sem sincronização adicional.
*/
public class CalculadoraImpl extends UnicastRemoteObject implements Calculadora {

   public CalculadoraImpl() throws RemoteException {
       super(); // exporta o objeto remoto
   }

   @Override
   public int soma(int a, int b) throws RemoteException {
       simulaProcessamento();
       return a + b;
   }

   @Override
   public int subtrai(int a, int b) throws RemoteException {
       simulaProcessamento();
       return a - b;
   }

   @Override
   public int multiplica(int a, int b) throws RemoteException {
       simulaProcessamento();
       return a * b;
   }

   @Override
   public int divide(int a, int b) throws RemoteException {
       simulaProcessamento();
       if (b == 0) throw new RemoteException("Divisão por zero!");
       return a / b;
   }

   /**
    * Simula uma operação demorada no servidor para evidenciar
    * o comportamento síncrono do RPC (cliente bloqueia).
    */
   private void simulaProcessamento() {
       try {
           System.out.printf("[SERVER] Thread=%s processando...%n",
                   Thread.currentThread().getName());
           Thread.sleep(3000); // 3s
       } catch (InterruptedException e) {
           Thread.currentThread().interrupt();
       }
   }
}