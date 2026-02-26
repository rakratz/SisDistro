package server;

import java.net.*;
import java.io.*;

public class Processador implements Runnable {
	
	private DatagramPacket pacote;
	private DatagramSocket socket;
	
	public Processador(DatagramPacket pacote, DatagramSocket socket) {
		this.pacote = pacote;
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			String mensagem = new String(pacote.getData(),0,pacote.getLength());
			int numero = Integer.parseInt(mensagem.trim());
			
			System.out.println("Recebido: " + numero +
					" | Thread: "+ Thread.currentThread().getId());
			
			// Processamento
			int resultado = numero * 2;
			
			String resposta = "Resultado: " + resultado;
			byte[] dadosResposta = resposta.getBytes();
			
			DatagramPacket pacoteResposta = new DatagramPacket(
					dadosResposta,
					dadosResposta.length,
					pacote.getAddress(),
					pacote.getPort()
					);
			
			socket.send(pacoteResposta);
			
		} catch (Exception e) {
            e.printStackTrace();
        }
	}

}
