package br.com.treinamento.aula1Spring.entities;

public class ExecutaProduto {
public static void main(String[] args) {
	Produto p = new Produto();
	p.setIdProduto(10);
	p.setNome("Computador");
	p.setPreco(1000.);
	p.setDescricao("lenovo");
	System.out.println(p);
	
}
}
