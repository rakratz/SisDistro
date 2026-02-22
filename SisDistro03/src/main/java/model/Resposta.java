package model;

import java.io.Serializable;
import java.util.List;

/*
 * Essa classe representa a resposta que o servidor envia de volta ao cliente.
 * 
 * Em sistemas distribuídos, comunicação é baseada em requisição → resposta.
 * 
 * O cliente envia uma Requisicao.
 * O servidor processa.
 * O servidor devolve uma Resposta.
 * 
 * Assim como a Requisicao, essa classe precisa ser Serializable
 * porque será enviada pela rede via ObjectOutputStream.
 */
public class Resposta implements Serializable {

    /*
     * Identificador de versão da classe.
     * 
     * Garante que cliente e servidor estejam usando
     * a mesma estrutura da classe durante a desserialização.
     */
    private static final long serialVersionUID = 1L;

    /*
     * mensagem é um texto explicando o resultado da operação.
     * 
     * Exemplo:
     * "Pessoa adicionada"
     * "Pessoa removida"
     * "Lista de pessoas"
     * 
     * Isso simula um status de resposta (como HTTP 200, 404, etc.)
     */
    private String mensagem;

    /*
     * pessoas representa os dados retornados pelo servidor.
     * 
     * Aqui estamos retornando a lista atualizada após a operação.
     * 
     * Observe que estamos enviando uma estrutura complexa (List<Pessoa>),
     * mostrando que a serialização permite trafegar objetos compostos.
     */
    private List<Pessoa> pessoas;

    /*
     * Construtor usado pelo servidor para montar a resposta.
     * 
     * O servidor cria esse objeto após processar a requisição
     * e o envia de volta ao cliente.
     */
    public Resposta(String mensagem, List<Pessoa> pessoas) {
        this.mensagem = mensagem;
        this.pessoas = pessoas;
    }

    /*
     * Getter usado pelo cliente para exibir a mensagem
     * retornada pelo servidor.
     */
    public String getMensagem() {
        return mensagem;
    }

    /*
     * Getter usado pelo cliente para acessar
     * os dados retornados pelo servidor.
     */
    public List<Pessoa> getPessoas() {
        return pessoas;
    }
}
