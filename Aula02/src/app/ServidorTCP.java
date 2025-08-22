package app;

import java.io.*;
import java.net.*;

public class ServidorTCP {
    public static void main(String[] args) {
        int port = 5000;
        try (ServerSocket servidor = new ServerSocket(port)) {
            System.out.println("Servidor aguardando conexão...");
            try (Socket socket = servidor.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

                System.out.println("Cliente conectado!");

                // Thread de leitura
                Thread leitor = new Thread(() -> {
                    try {
                        String msg;
                        while ((msg = in.readLine()) != null) {
                            if (msg.isEmpty()) break;
                            System.out.println("[CLIENTE] " + msg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Conexão encerrada.");
                    System.exit(0);
                });

                // Thread de escrita
                Thread escritor = new Thread(() -> {
                    try {
                        String msg;
                        while ((msg = teclado.readLine()) != null) {
                            out.println(msg);
                            if (msg.isEmpty()) break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                leitor.start();
                escritor.start();
                leitor.join();
                escritor.join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
