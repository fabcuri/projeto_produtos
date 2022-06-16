package br.com.treinamento.aula1Spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.treinamento.aula1Spring.entities.Produto;

@Repository
public interface IProdutoRepository extends CrudRepository<Produto,Integer>{

}
