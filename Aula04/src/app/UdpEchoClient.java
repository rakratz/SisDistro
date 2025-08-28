package app;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UdpEchoClient {
    public static void main(String[] args) throws Exception {
        String host = (args.length > 0) ? args[0] : "localhost";
        int port = 5000;

        try (DatagramSocket socket = new DatagramSocket();
             Scanner sc = new Scanner(System.in)) {

            InetAddress server = InetAddress.getByName(host);
            System.out.println("[CLIENT] Envie mensagens. Enter vazio sai.");

            while (true) {
                System.out.print("msg> ");
                String line = sc.nextLine();
                if (line == null || line.trim().isEmpty()) break;

                // envia
                byte[] data = line.getBytes(StandardCharsets.UTF_8);
                DatagramPacket pkt = new DatagramPacket(data, data.length, server, port);
                socket.send(pkt);

                // recebe resposta
                byte[] buf = new byte[1024];
                DatagramPacket resp = new DatagramPacket(buf, buf.length);
                socket.receive(resp);
                String ans = new String(resp.getData(), 0, resp.getLength(), StandardCharsets.UTF_8);
                System.out.println("[SERVER] " + ans);
            }
        }
    }
}
