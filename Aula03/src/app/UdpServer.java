package app;

import java.net.*;

public class UdpServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(5000);
        System.out.println("[SERVIDOR UDP] Aguardando mensagem...");

        byte[] buffer = new byte[1024];
        DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);

        socket.receive(pacote); // espera mensagem
        String recebido = new String(pacote.getData(), 0, pacote.getLength());

        System.out.println("Mensagem recebida: " + recebido);

        socket.close();
    }
}
