package app;

import java.net.*;
import java.nio.charset.StandardCharsets;

public class UdpEchoServer {
    public static void main(String[] args) throws Exception {
        int port = 5000;
        byte[] buffer = new byte[1024];

        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("[SERVER] UDP escutando porta " + port);

            while (true) {
                // 1) recebe
                DatagramPacket recv = new DatagramPacket(buffer, buffer.length);
                socket.receive(recv); // bloqueia até chegar algo

                String msg = new String(recv.getData(), 0, recv.getLength(), StandardCharsets.UTF_8);
                System.out.println("[SERVER] Recebido de " + recv.getAddress() + ":" + recv.getPort() + " -> " + msg);

                // 2) responde (eco)
                byte[] out = ("ECO: " + msg).getBytes(StandardCharsets.UTF_8);
                DatagramPacket send = new DatagramPacket(out, out.length, recv.getAddress(), recv.getPort());
                socket.send(send);
            }
        }
    }
}
