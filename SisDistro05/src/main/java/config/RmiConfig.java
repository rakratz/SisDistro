package config;

public class RmiConfig {

	public RmiConfig() {
	}
	
	// IP ou alias (Host name) do Servidor 
	public static final String HOST = "localhost";
	public static final int PORT = 1099;
	public static final String SERVICE_NAME = "CalculadoraService";
	
	public static String serviceUrl() {
		return String.format("rmi://%s:%d/%s", HOST, PORT, SERVICE_NAME);
	}

}
