package br.org.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.blogpessoal.model.Usuario;
import br.org.generation.blogpessoal.model.UsuarioLogin;
import br.org.generation.blogpessoal.repository.UsuarioRepository;
import br.org.generation.blogpessoal.service.UsuarioService;

/**
 * Controlador responsável pelos endpoints relacionados aos usuários do blog.
 * Este controlador gerencia as operações CRUD para usuários, permitindo listar,
 * buscar, criar, atualizar, autenticar e outras operações relacionadas aos usuários.
 */
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins="*", allowedHeaders ="*")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;

	/**
	 * Lista todos os usuários cadastrados no blog.
	 *
	 * @return Uma lista de usuários.
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> getAll(){
		return ResponseEntity.ok(usuarioRepository.findAll());
	}

	/**
	 * Busca um usuário específico pelo seu ID.
	 *
	 * @param id O ID do usuário desejado.
	 * @return O usuário encontrado ou um erro 404 caso não exista.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable Long id){
		return usuarioRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

	/**
	 * Cadastra um novo usuário no blog.
	 *
	 * @param usuario Os dados do usuário a ser cadastrado.
	 * @return O usuário cadastrado ou um erro caso já exista um usuário com o mesmo nome de usuário.
	 */
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario){
		return usuarioService.cadastrarUsuario(usuario)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	/**
	 * Atualiza os dados de um usuário existente.
	 *
	 * @param usuario Os dados atualizados do usuário.
	 * @return O usuário atualizado ou um erro 404 caso não exista.
	 */
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario){
		return usuarioService.atualizarUsuario(usuario)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	/**
	 * Autentica um usuário existente.
	 *
	 * @param usuarioLogin Os dados para autenticação do usuário.
	 * @return Os dados do usuário autenticado ou um erro 401 caso a autenticação falhe.
	 */
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> login(@RequestBody Optional<UsuarioLogin> usuarioLogin){
		return usuarioService.autenticarUsuario(usuarioLogin)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
}