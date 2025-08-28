package app2;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UdpChatClient {
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) throws Exception {
        // Uso: java UdpChatClient <host> <apelido>
        String host = (args.length > 0) ? args[0] : "localhost";
        String nick = (args.length > 1) ? args[1] : ("user" + (int)(Math.random()*1000));

        InetAddress server = InetAddress.getByName(host);
        try (DatagramSocket socket = new DatagramSocket(); Scanner sc = new Scanner(System.in)) {
            System.out.println("[CLIENT] Conectando em " + host + ":" + SERVER_PORT + " como " + nick);
            send(socket, server, "HELLO " + nick);

            // Thread de recepção (imprime mensagens do servidor/outros clientes)
            Thread rx = new Thread(() -> {
                byte[] buf = new byte[2048];
                while (!Thread.currentThread().isInterrupted()) {
                    DatagramPacket p = new DatagramPacket(buf, buf.length);
                    try {
                        socket.receive(p);
                        String msg = new String(p.getData(), p.getOffset(), p.getLength(), StandardCharsets.UTF_8);
                        System.out.println("\n" + msg);
                        System.out.print("fale> ");
                    } catch (Exception e) {
                        break;
                    }
                }
            }, "udp-client-rx");
            rx.setDaemon(true);
            rx.start();

            // Loop de envio do teclado
            while (true) {
                System.out.print("fale> ");
                String line = sc.nextLine();
                if (line == null || line.trim().isEmpty()) {
                    send(socket, server, "BYE");
                    break;
                }
                send(socket, server, "MSG " + line);
            }
            rx.interrupt();
        }
        System.out.println("[CLIENT] Encerrado.");
    }

    static void send(DatagramSocket socket, InetAddress server, String msg) throws Exception {
        byte[] data = msg.getBytes(StandardCharsets.UTF_8);
        DatagramPacket pkt = new DatagramPacket(data, data.length, server, SERVER_PORT);
        socket.send(pkt);
    }
}

