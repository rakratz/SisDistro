package config;

/**
 * Configurações centrais de RMI: host, porta e nome do serviço.
 * Deixe tudo em um lugar só para facilitar mudanças.
 */
public final class RmiConfig {
    private RmiConfig() {}

    /** HOST do servidor (mude para o IP real se rodar em máquinas diferentes). */
    public static final String HOST = "localhost";

    /** Porta padrão do RMI Registry. */
    public static final int PORT = 1099;

    /** Nome do serviço publicado no Registry. */
    public static final String SERVICE_NAME = "CalculadoraService";

    /** URL completa no formato rmi://host:porta/nome */
    public static String serviceUrl() {
        return String.format("rmi://%s:%d/%s", HOST, PORT, SERVICE_NAME);
    }
}