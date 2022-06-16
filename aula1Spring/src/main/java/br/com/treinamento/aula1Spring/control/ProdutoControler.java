package br.com.treinamento.aula1Spring.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import io.swagger.annotations.ApiOperation;


import br.com.treinamento.aula1Spring.entities.Produto;
import br.com.treinamento.aula1Spring.repositories.IProdutoRepository;
import br.com.treinamento.aula1Spring.request.*;

@Controller

public class ProdutoControler {
	@Autowired
	private IProdutoRepository produtosRepository;
	//metodo para
	private static final String ENDPOINT= "api/produtos";



	
//metodo para realizar o serviço de cadastro de produto
	@CrossOrigin
	@RequestMapping(value=ENDPOINT, method=RequestMethod.POST)
	@ApiOperation("Serviço para cadastro de produto")
	public ResponseEntity<String>post(@RequestBody ProdutoPostRequest request){
		try {
			Produto produto= new Produto();
			produto.setNome(request.getNome());
			produto.setPreco(request.getPreco());
			produto.setQuantidade(request.getQuantidade());
			produto.setDescricao(request.getDescricao());

			produtosRepository.save(produto);

			return ResponseEntity.status(HttpStatus.OK).body("Produto cadastrado com sucesso.");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro:"+e.getMessage());
		}
	}

	//metodo para realizar o serviço de edicao de produto
	@CrossOrigin
	@RequestMapping(value=ENDPOINT, method=RequestMethod.PUT)
	@ApiOperation("Serviço para edição de produto")
	public ResponseEntity<String>put(@RequestBody ProdutoPutRequest request){
		try {
			//consultar o produto no banco atraves do ID
			Optional<Produto> item= produtosRepository.findById(request.getIdProduto());
			if(!item.isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto não encontrado");
			}else {
				Produto produto=item.get();
				produto.setNome(request.getNome());
				produto.setPreco(request.getPreco());
				produto.setQuantidade(request.getQuantidade());
				produto.setDescricao(request.getDescricao());

				produtosRepository.save(produto);
				return ResponseEntity.status(HttpStatus.OK).body("Produto atualizado com sucesso");

			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro:"+e.getMessage());
		}
	}
	//metodo para realizar o serviço de exclusao de produto
	@CrossOrigin
	@RequestMapping(value=ENDPOINT + "/{idProduto}", method=RequestMethod.DELETE)
	@ApiOperation("Serviço para exclusão de produto")
	public ResponseEntity<String>delete(@PathVariable("idProduto") Integer idProduto){
		try {
			//consultar o produto no banco
			Optional<Produto> item= produtosRepository.findById(idProduto);
			//verificar se o produto não foi encontrado
			if(!item.isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto não encontrado");
			}else {
				Produto produto= item.get();
				produtosRepository.delete(produto);
				return ResponseEntity.status(HttpStatus.OK).body("Produto excluido com sucesso");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro:"+e.getMessage());
		}

	}
	//metodo para realizar a consulta de produto
	@CrossOrigin
	@RequestMapping(value=ENDPOINT, method=RequestMethod.GET)
	@ApiOperation("Serviço para consulta de todos os produtos")
	public ResponseEntity<List<ProdutoGetResponse>>get() {
		List<ProdutoGetResponse> response= new ArrayList<ProdutoGetResponse>();
		for(Produto produto: produtosRepository.findAll()) {
			ProdutoGetResponse item= new ProdutoGetResponse();
			item.setIdProduto(produto.getIdProduto());
			item.setNome(produto.getNome());
			item.setDescricao(produto.getDescricao());
			item.setPreco(produto.getPreco());
			item.setQuantidade(produto.getQuantidade());
			item.setTotal(produto.getPreco()*produto.getQuantidade());

			response.add(item);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	//metodo para realizar a consulta de produto baseado no id
	@CrossOrigin
	@RequestMapping(value=ENDPOINT + "/{idProduto}", method=RequestMethod.GET)
	@ApiOperation("Serviço para consulta de produto por Id")
	public ResponseEntity<ProdutoGetResponse>getById(@PathVariable("idProduto") Integer idProduto) {
		//consultar o produto no banco
		Optional<Produto> item= produtosRepository.findById(idProduto);
		//verificar se o produto existe
		if(!item.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}else {
			ProdutoGetResponse response= new ProdutoGetResponse();
			Produto produto= item.get();
			response.setIdProduto(produto.getIdProduto());
			response.setNome(produto.getNome());
			response.setDescricao(produto.getDescricao());
			response.setPreco(produto.getPreco());
			response.setQuantidade(produto.getQuantidade());
			response.setTotal(produto.getPreco()*produto.getQuantidade());
			return ResponseEntity.status(HttpStatus.OK).body(response);

		}
	}
}




