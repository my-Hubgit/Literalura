package br.com.claudemir.literalura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Classe principal que inicializa a aplicação
@SpringBootApplication(scanBasePackages = "br.com.claudemir.literalura")
public class LiteraluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}
}
