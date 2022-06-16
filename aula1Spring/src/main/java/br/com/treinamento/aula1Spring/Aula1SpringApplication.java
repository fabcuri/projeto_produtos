package br.com.treinamento.aula1Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@EnableWebMvc
@SpringBootApplication
public class Aula1SpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(Aula1SpringApplication.class, args);
	}

}
