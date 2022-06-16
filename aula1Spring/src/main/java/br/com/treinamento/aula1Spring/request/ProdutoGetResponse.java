package br.com.treinamento.aula1Spring.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProdutoGetResponse {
	private Integer idProduto;
	private String nome;
	private String descricao;
	private Double preco;
	private Integer quantidade;
	private Double total;

}
