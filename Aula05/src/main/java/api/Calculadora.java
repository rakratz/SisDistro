package api;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Contrato remoto da Calculadora.
 *
 * Esta interface define os métodos que poderão ser invocados
 * remotamente pelos clientes via RMI. Por isso:
 *  - estende java.rmi.Remote
 *  - todos os métodos declaram throws RemoteException
 */
public interface Calculadora extends Remote {

    /**
     * Soma dois inteiros remotamente.
     */
    int soma(int a, int b) throws RemoteException;

    /**
     * Subtrai b de a remotamente.
     */
    int subtrai(int a, int b) throws RemoteException;

    /**
     * Multiplica dois inteiros remotamente.
     */
    int multiplica(int a, int b) throws RemoteException;

    /**
     * Divide a por b remotamente.
     * @throws RemoteException se b == 0
     */
    int divide(int a, int b) throws RemoteException;
}