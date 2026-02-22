package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import model.*;

/*
 * Esta classe representa o CLIENTE da aplicação distribuída.
 * 
 * O cliente:
 * 1) Coleta dados do usuário.
 * 2) Abre conexão com o servidor.
 * 3) Envia uma Requisicao.
 * 4) Aguarda a Resposta.
 * 5) Exibe o resultado.
 */
public class Cliente {

    public static void main(String[] args) {

        /*
         * Scanner apenas para simular uma interface simples.
         * Aqui estamos criando uma aplicação cliente de terminal.
         */
        Scanner sc = new Scanner(System.in);

        System.out.println("1-CREATE 2-READ 3-UPDATE 4-DELETE");
        int opcao = sc.nextInt();
        sc.nextLine(); // limpa buffer

        /*
         * Aqui estamos traduzindo a opção numérica
         * para o protocolo de aplicação (String).
         */
        String operacao = "";

        switch (opcao) {
            case 1: operacao = "CREATE"; break;
            case 2: operacao = "READ"; break;
            case 3: operacao = "UPDATE"; break;
            case 4: operacao = "DELETE"; break;
        }

        /*
         * Dependendo da operação, precisamos ou não
         * enviar um objeto Pessoa.
         */
        Pessoa pessoa = null;

        if (!operacao.equals("READ")) {

            System.out.print("Nome: ");
            String nome = sc.nextLine();

            int idade = 0;

            /*
             * DELETE só precisa do nome.
             * UPDATE e CREATE precisam de idade.
             */
            if (!operacao.equals("DELETE")) {
                System.out.print("Idade: ");
                idade = sc.nextInt();
            }

            pessoa = new Pessoa(nome, idade);
        }

        /*
         * Aqui começa a parte de rede.
         * 
         * Ao criar o Socket:
         * - Cliente envia pedido de conexão (TCP handshake).
         * - Se o servidor estiver ouvindo na porta 12345,
         *   a conexão será estabelecida.
         */
        try (Socket socket = new Socket("localhost", 12345)) {

            /*
             * Streams para enviar e receber objetos.
             * 
             * ObjectOutputStream → SERIALIZA
             * ObjectInputStream → DESSERIALIZA
             */
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            /*
             * Criamos o objeto Requisicao.
             * Esse objeto representa nossa mensagem de rede.
             */
            Requisicao req = new Requisicao(operacao, pessoa);

            /*
             * Enviamos a requisição ao servidor.
             * Aqui o objeto é convertido em bytes.
             */
            oos.writeObject(req);
            oos.flush();

            /*
             * Agora o cliente fica BLOQUEADO aguardando resposta.
             * 
             * Comunicação síncrona.
             */
            Resposta resp = (Resposta) ois.readObject();

            /*
             * Exibimos a resposta retornada pelo servidor.
             */
            System.out.println("\nServidor respondeu: " + resp.getMensagem());
            System.out.println("Lista atual:");

            for (Pessoa p : resp.getPessoas()) {
                System.out.println(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
         * Encerramos o Scanner.
         */
        sc.close();
    }
}
