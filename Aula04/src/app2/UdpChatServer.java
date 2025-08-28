package app2;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.Map;
import java.util.concurrent.*;

public class UdpChatServer {
    private static final int PORT = 5000;
    private static final int BUF  = 2048;

    // Conjunto de clientes ativos (endereço + porta)
    private final Set<SocketAddress> clients = ConcurrentHashMap.newKeySet();
    // Apelidos por cliente
    private final Map<SocketAddress, String> nickByAddr = new ConcurrentHashMap<>();

    private DatagramSocket socket;

    public static void main(String[] args) throws Exception {
        new UdpChatServer().run();
    }

    void run() throws Exception {
        socket = new DatagramSocket(PORT);
        System.out.println("[SERVER] UDP ouvindo em *:" + PORT);
        System.out.println("[SERVER] Digite no console para enviar broadcast. Enter vazio encerra o servidor.");

        // Thread 1: recebe pacotes dos clientes
        Thread rx = new Thread(this::receiveLoop, "udp-rx");
        rx.setDaemon(true);
        rx.start();

        // Thread 2: leitura do teclado do professor para broadcast
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.print("servidor> ");
                String line = console.readLine();
                if (line == null || line.trim().isEmpty()) {
                    broadcast("[SERVER] Encerrando...");
                    break;
                }
                broadcast("[SERVER] " + line);
            }
        } finally {
            socket.close();
        }
        System.out.println("[SERVER] Finalizado.");
    }

    void receiveLoop() {
        byte[] buf = new byte[BUF];
        while (!socket.isClosed()) {
            DatagramPacket p = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(p); // bloqueia até chegar algo
                // Copia os bytes do pacote (o array 'buf' será reutilizado)
                String msg = new String(p.getData(), p.getOffset(), p.getLength(), StandardCharsets.UTF_8).trim();
                SocketAddress who = p.getSocketAddress();

                // Protocolo didático:
                // "HELLO <nick>"  -> registrar cliente
                // "MSG <texto>"   -> mensagem pública
                // "BYE"           -> sair
                if (msg.toUpperCase().startsWith("HELLO")) {
                    String nick = msg.length() > 5 ? msg.substring(5).trim() : "anon";
                    clients.add(who);
                    nickByAddr.put(who, nick);
                    System.out.println("[JOIN] " + nick + " " + who);
                    broadcastExcept("[GREEN] " + nick + " entrou no chat.", who);
                    send(who, "Bem-vindo, " + nick + "! Digite mensagens; Enter vazio encerra.");
                } else if (msg.toUpperCase().startsWith("MSG")) {
                    String text = msg.length() > 3 ? msg.substring(3).trim() : "";
                    if (text.isEmpty()) continue;
                    String nick = nickByAddr.getOrDefault(who, who.toString());
                    broadcast("[" + nick + "] " + text);
                } else if (msg.equalsIgnoreCase("BYE")) {
                    String nick = nickByAddr.getOrDefault(who, who.toString());
                    clients.remove(who);
                    nickByAddr.remove(who);
                    System.out.println("[LEAVE] " + nick + " " + who);
                    broadcast("[RED] " + nick + " saiu do chat.");
                } else {
                    // Se não registrou ainda, faz registro rápido com apelido derivado
                    if (!clients.contains(who)) {
                        String autoNick = "user@" + ((InetSocketAddress) who).getPort();
                        clients.add(who);
                        nickByAddr.put(who, autoNick);
                        broadcastExcept("[GREEN] " + autoNick + " entrou (auto).", who);
                        send(who, "Use: HELLO <apelido>, MSG <texto>, BYE");
                    } else {
                        String nick = nickByAddr.getOrDefault(who, who.toString());
                        broadcast("[" + nick + "] " + msg); // repassa cru
                    }
                }
            } catch (Exception e) {
                if (!socket.isClosed()) System.out.println("[SERVER] Erro RX: " + e.getMessage());
            }
        }
    }

    void send(SocketAddress to, String msg) {
        try {
            byte[] data = msg.getBytes(StandardCharsets.UTF_8);
            DatagramPacket resp = new DatagramPacket(data, data.length, (InetSocketAddress) to);
            socket.send(resp);
        } catch (Exception ignored) {}
    }

    void broadcast(String msg) {
        for (SocketAddress c : clients) send(c, msg);
        System.out.println("[BCAST] " + msg);
    }

    void broadcastExcept(String msg, SocketAddress except) {
        for (SocketAddress c : clients) if (!c.equals(except)) send(c, msg);
        System.out.println("[BCAST] " + msg + " (exceto " + except + ")");
    }
}
