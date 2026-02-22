package model;

import java.io.Serializable;

/*
 * Essa classe representa a "mensagem" que o cliente envia para o servidor.
 * 
 * Em sistemas distribuídos, a comunicação acontece através de troca de mensagens.
 * Aqui estamos modelando essa mensagem como um objeto Java.
 * 
 * Como estamos usando Socket + ObjectOutputStream,
 * precisamos que essa classe seja serializável.
 */
public class Requisicao implements Serializable {

    /*
     * serialVersionUID é um identificador da versão da classe.
     * 
     * Ele garante compatibilidade durante o processo de desserialização.
     * 
     * Se o cliente e o servidor tiverem versões diferentes da classe
     * e o serialVersionUID não bater, ocorre InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

    /*
     * operacao representa qual ação o cliente deseja executar.
     * 
     * Estamos usando um protocolo simples baseado em String:
     * CREATE, READ, UPDATE, DELETE
     * 
     * Isso simula um protocolo de aplicação.
     */
    private String operacao;

    /*
     * pessoa representa o "payload" da requisição.
     * 
     * Nem toda operação precisa dela (ex: READ),
     * mas mantemos para simplificar o modelo.
     */
    private Pessoa pessoa;

    /*
     * Construtor usado pelo cliente para montar a requisição.
     * 
     * Aqui estamos criando o objeto que será serializado
     * e enviado através do Socket.
     */
    public Requisicao(String operacao, Pessoa pessoa) {
        this.operacao = operacao;
        this.pessoa = pessoa;
    }

    /*
     * Getter usado pelo servidor para saber
     * qual operação foi solicitada.
     */
    public String getOperacao() {
        return operacao;
    }

    /*
     * Getter usado pelo servidor para acessar
     * os dados enviados pelo cliente.
     */
    public Pessoa getPessoa() {
        return pessoa;
    }
}
