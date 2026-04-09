package publisher;

import jakarta.xml.ws.Endpoint;
import service.MatriculaServiceImpl;

public class Publicador {

    public static void main(String[] args) {

        String url = "http://localhost:8080/matricula";

        Endpoint.publish(url, new MatriculaServiceImpl());

        System.out.println("Servico rodando!");
        System.out.println(url + "?wsdl");
    }
}
