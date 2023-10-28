package br.org.generation.blogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.blogpessoal.model.Postagem;
import br.org.generation.blogpessoal.repository.PostagemRepository;

/**
 * Controlador responsável pelos endpoints relacionados às postagens do blog.
 * Este controlador gerencia as operações CRUD para postagens, permitindo listar,
 * buscar, criar, atualizar e deletar postagens.
 */
@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

	@Autowired
	private PostagemRepository postagemRepository;

	/**
	 * Lista todas as postagens disponíveis no blog.
	 *
	 * @return Uma lista de postagens.
	 */
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll() {
		return ResponseEntity.ok(postagemRepository.findAll());
	}

	/**
	 * Busca uma postagem específica pelo seu ID.
	 *
	 * @param id O ID da postagem desejada.
	 * @return A postagem encontrada ou um erro 404 caso não exista.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id) {
		return postagemRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

	/**
	 * Lista postagens que contêm um determinado título.
	 *
	 * @param titulo O trecho do título a ser buscado.
	 * @return Uma lista de postagens que correspondem ao título fornecido.
	 */
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));

	}

	/**
	 * Cria uma nova postagem no blog.
	 *
	 * @param postagem Os dados da postagem a ser criada.
	 * @return A postagem criada.
	 */
	@PostMapping
	public ResponseEntity<Postagem> postPostagem(@Valid @RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
	}

	/**
	 * Atualiza os dados de uma postagem existente.
	 *
	 * @param postagem Os dados atualizados da postagem.
	 * @return A postagem atualizada ou um erro 404 caso não exista.
	 */
	@PutMapping
	public ResponseEntity<Postagem> putPostagem(@Valid @RequestBody Postagem postagem) {
		// return
		// ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
		return postagemRepository.findById(postagem.getId())
				.map(resposta -> ResponseEntity.ok(postagemRepository.save(postagem)))
				.orElse(ResponseEntity.notFound().build());
	}

	/**
	 * Deleta uma postagem específica pelo seu ID.
	 *
	 * @param id O ID da postagem a ser deletada.
	 * @return Uma resposta sem conteúdo, indicando a exclusão bem-sucedida, ou um erro 404 caso a postagem não exista.
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<?> deletePostagem(@PathVariable Long id) {
		return postagemRepository.findById(id).map(resposta -> {
			postagemRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}).orElse(ResponseEntity.notFound().build());

	}
}