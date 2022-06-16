package br.com.treinamento.aula1Spring.control;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.treinamento.aula1Spring.entities.Usuario;
import br.com.treinamento.aula1Spring.repositories.IUsuarioRepository;
import br.com.treinamento.aula1Spring.request.AccountPostRequest;
import io.swagger.annotations.ApiOperation;

@Controller
@Transactional
public class AccountController {
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	private static final String ENDPOINT= "/api/account";
	@ApiOperation  ("Serviço de criação de conta de usuario.")
	@RequestMapping(value= ENDPOINT, method=RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<String> post(@RequestBody AccountPostRequest request){
	try {
		if(usuarioRepository.findByLogin(request.getLogin()) !=null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O login informado ja esta cadastrado no sistema, tente outro");
		}
		Usuario usuario= new Usuario();
		usuario.setNome(request.getNome());
		usuario.setLogin(request.getLogin());
		usuario.setSenha(request.getSenha());
		
		usuarioRepository.save(usuario);
		
		return ResponseEntity.status(HttpStatus.OK).body("Conta de usuario criada com sucesso");
	} catch (Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}
		
	}
}
