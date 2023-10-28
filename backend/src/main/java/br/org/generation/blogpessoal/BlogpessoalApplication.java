package br.org.generation.blogpessoal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal de inicialização da aplicação Blog Pessoal.
 * Esta classe é responsável por iniciar a aplicação Spring Boot.
 */
@SpringBootApplication
public class BlogpessoalApplication {

	/**
	 * Método principal que inicia a aplicação.
	 *
	 * @param args Argumentos de linha de comando.
	 */
	public static void main(String[] args) {
		SpringApplication.run(BlogpessoalApplication.class, args);
	}

}
