package br.edu.ipog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title="Tweets API - Distro Classe", 
version = "1.0", description = "API for Tweet's"))
public class SprintTweetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintTweetApplication.class, args);
	}

}
