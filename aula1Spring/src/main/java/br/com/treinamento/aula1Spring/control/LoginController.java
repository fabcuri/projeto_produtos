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
import br.com.treinamento.aula1Spring.request.LoginPostRequest;
import br.com.treinamento.aula1String.security.MD5Cryptography;
import io.swagger.annotations.ApiOperation;

@Controller
@Transactional
public class LoginController {

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	private static final String ENDPOINT = "/api/login";
	
	
	@ApiOperation("Servico para autenticação de Usuario")
	@RequestMapping (value=ENDPOINT, method= RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<String> post(@RequestBody LoginPostRequest request){
		try {
			Usuario usuario= usuarioRepository
					.findByLoginAndSenha(request.getLogin(), MD5Cryptography.encrypt(request.getSenha()));
		
			if(usuario !=null) {
				return ResponseEntity.status(HttpStatus.OK).body("Usuario autenticado com sucesso");
			}else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso Negado");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
