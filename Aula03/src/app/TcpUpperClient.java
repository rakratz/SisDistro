package app;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TcpUpperClient {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 5000;

        Socket socket = new Socket(host, port);
        System.out.println("[CLIENTE TCP] Conectado ao servidor!");

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Scanner sc = new Scanner(System.in);
        System.out.print("Digite uma frase: ");
        String mensagem = sc.nextLine();

        out.println(mensagem);
        String resposta = in.readLine();
        System.out.println("Resposta do servidor: " + resposta);

        socket.close();
    }
}
