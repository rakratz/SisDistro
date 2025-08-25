package app;

import java.io.*;
import java.net.*;

public class TcpUpperServer {
    public static void main(String[] args) throws IOException {
        int port = 5000;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("[SERVIDOR TCP] Aguardando conexão...");

        Socket socket = serverSocket.accept();
        System.out.println("[SERVIDOR TCP] Cliente conectado!");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String mensagem = in.readLine();
        System.out.println("Recebido: " + mensagem);

        // Responde em maiúsculas
        String resposta = mensagem.toUpperCase();
        out.println(resposta);

        socket.close();
        serverSocket.close();
        System.out.println("[SERVIDOR TCP] Conexão encerrada.");
    }
}
