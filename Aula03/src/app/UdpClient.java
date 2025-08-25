package app;

import java.net.*;
import java.util.Scanner;

public class UdpClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");

        Scanner sc = new Scanner(System.in);
        System.out.print("Digite uma frase: ");
        String msg = sc.nextLine();

        byte[] buffer = msg.getBytes();
        DatagramPacket pacote = new DatagramPacket(buffer, buffer.length, ip, 5000);

        socket.send(pacote);
        System.out.println("[CLIENTE UDP] Mensagem enviada.");

        socket.close();
    }
}

