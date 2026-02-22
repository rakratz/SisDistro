package server;

import java.io.*;
import java.net.*;
import java.util.*;
import model.*;

/*
 * Esta classe representa o SERVIDOR da aplicação distribuída.
 * 
 * Ele:
 * 1) Fica aguardando conexões de clientes.
 * 2) Recebe uma requisição.
 * 3) Processa a operação (CRUD).
 * 4) Salva os dados em arquivo.
 * 5) Retorna uma resposta ao cliente.
 */
public class Servidor {

    /*
     * Arquivo onde os dados serão persistidos.
     * 
     * Apesar do nome .txt, estamos salvando dados binários
     * usando serialização.
     */
    private static final String ARQUIVO = "pessoas.txt";

    /*
     * Estrutura de dados mantida em memória.
     * 
     * Essa lista funciona como um "banco de dados em memória".
     */
    private static List<Pessoa> pessoas = new ArrayList<>();

    public static void main(String[] args) {

        /*
         * Ao iniciar o servidor,
         * tentamos carregar dados previamente salvos.
         */
        carregarArquivo();

        /*
         * ServerSocket abre a porta 12345
         * e fica aguardando conexões.
         */
        try (ServerSocket serverSocket = new ServerSocket(12345)) {

            System.out.println("Servidor rodando na porta 12345...");

            /*
             * Loop infinito.
             * 
             * O servidor nunca para.
             * Ele fica continuamente aceitando novas conexões.
             */
            while (true) {

                /*
                 * accept() é BLOQUEANTE.
                 * 
                 * O servidor fica parado aqui
                 * até que um cliente se conecte.
                 */
                Socket socket = serverSocket.accept();

                System.out.println("Cliente conectado: "
                        + socket.getInetAddress().getHostAddress());

                /*
                 * Criamos os streams de comunicação.
                 * 
                 * ObjectInputStream → recebe objetos
                 * ObjectOutputStream → envia objetos
                 */
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                /*
                 * Recebemos a requisição enviada pelo cliente.
                 * Aqui ocorre a DESSERIALIZAÇÃO.
                 */
                Requisicao req = (Requisicao) ois.readObject();

                /*
                 * Processamos a operação solicitada.
                 */
                Resposta resp = processar(req);

                /*
                 * Persistimos os dados após qualquer alteração.
                 */
                salvarArquivo();

                /*
                 * Enviamos a resposta ao cliente.
                 * Aqui ocorre SERIALIZAÇÃO.
                 */
                oos.writeObject(resp);
                oos.flush();

                /*
                 * Encerramos a conexão.
                 * Nosso modelo é síncrono e de curta duração.
                 */
                socket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Método responsável por interpretar a requisição
     * e executar a operação solicitada.
     */
    private static Resposta processar(Requisicao req) {

        String op = req.getOperacao();
        Pessoa p = req.getPessoa();

        /*
         * Aqui estamos implementando o protocolo de aplicação.
         * 
         * Dependendo da String enviada pelo cliente,
         * executamos uma operação diferente.
         */
        switch (op) {

            case "CREATE":
                pessoas.add(p);
                return new Resposta("Pessoa adicionada!", pessoas);

            case "READ":
                return new Resposta("Lista de pessoas", pessoas);

            case "UPDATE":
                for (Pessoa pessoa : pessoas) {
                    if (pessoa.getNome().equals(p.getNome())) {
                        pessoa.setIdade(p.getIdade());
                        return new Resposta("Pessoa atualizada!", pessoas);
                    }
                }
                return new Resposta("Pessoa não encontrada", pessoas);

            case "DELETE":
                pessoas.removeIf(pessoa -> pessoa.getNome().equals(p.getNome()));
                return new Resposta("Pessoa removida!", pessoas);

            default:
                return new Resposta("Operação inválida", pessoas);
        }
    }

    /*
     * Salva a lista inteira no arquivo.
     * 
     * Isso substitui completamente o conteúdo anterior.
     */
    private static void salvarArquivo() {
        try (ObjectOutputStream oos =
                new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {

            oos.writeObject(pessoas);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Carrega dados previamente salvos.
     * 
     * Se o arquivo não existir,
     * iniciamos com lista vazia.
     */
    private static void carregarArquivo() {
        try (ObjectInputStream ois =
                new ObjectInputStream(new FileInputStream(ARQUIVO))) {

            pessoas = (List<Pessoa>) ois.readObject();

        } catch (Exception e) {
            pessoas = new ArrayList<>();
        }
    }
}
