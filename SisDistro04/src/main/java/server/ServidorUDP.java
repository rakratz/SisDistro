package server;

import java.net.*;
import java.io.*;

public class ServidorUDP {

	public static void main(String[] args) throws Exception{
		// Criar meu Servidor Socket UDP
		DatagramSocket socket = new DatagramSocket(5000);
		System.out.println("Servidor UDP inciado na porta 5000!");
		
		// Loop (receber as requisições dos clientes)
		while(true) {
			byte[] buffer = new byte[1024];
			DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);
			socket.receive(pacote);
			
			// Criar um thread para processar essa requisição
			 new Thread(new Processador(pacote, socket)).start();
		}
	}

}
