package client;

import java.net.*;
import java.io.*;

public class ClienteUDP {

	public static void main(String[] args) throws Exception{
		
		DatagramSocket socket = new DatagramSocket();

		String mensage = "30";
		
		byte[] dados = mensage.getBytes();
		
		InetAddress endereco = InetAddress.getByName("10.42.27.252");
		
		DatagramPacket pacote = new DatagramPacket(
				dados,
				dados.length,
				endereco,
				5000
				);
		
		socket.send(pacote);
		
		byte[] buffer = new byte[1024];
		DatagramPacket resposta = new DatagramPacket(buffer, buffer.length);
		socket.receive(resposta);
		
			String msgResposta = new String(
					resposta.getData(),0,resposta.getLength());
		
		System.out.println("Recebido: " + msgResposta);
	}

}

