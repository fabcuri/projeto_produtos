package br.com.treinamento.aula1Spring.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountPostRequest {
	private String nome;
	private String login;
	private String senha;

}
