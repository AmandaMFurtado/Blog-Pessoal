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

import br.org.generation.blogpessoal.model.Tema;
import br.org.generation.blogpessoal.repository.TemaRepository;

/**
 * Controlador responsável pelos endpoints relacionados aos temas do blog.
 * Este controlador gerencia as operações CRUD para temas, permitindo listar,
 * buscar, criar, atualizar e deletar temas.
 */
@RestController
@RequestMapping("/temas")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class TemaController {
	
	@Autowired
	private TemaRepository temaRepository;

	/**
	 * Lista todos os temas disponíveis no blog.
	 *
	 * @return Uma lista de temas.
	 */
	@GetMapping
	private ResponseEntity<List<Tema>> getAll(){
		return ResponseEntity.ok(temaRepository.findAll());
	}

	/**
	 * Busca um tema específico pelo seu ID.
	 *
	 * @param id O ID do tema desejado.
	 * @return O tema encontrado ou um erro 404 caso não exista.
	 */
	@GetMapping("/{id}")
	private ResponseEntity<Tema> getById(@PathVariable Long id){
		return temaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

	/**
	 * Lista temas que contêm uma determinada descrição.
	 *
	 * @param descricao O trecho da descrição a ser buscado.
	 * @return Uma lista de temas que correspondem à descrição fornecida.
	 */
	@GetMapping("/descricao/{descricao}")
	private ResponseEntity<List<Tema>> getByDescricao(@PathVariable String descricao){
		return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}

	/**
	 * Cria um novo tema no blog.
	 *
	 * @param tema Os dados do tema a ser criado.
	 * @return O tema criado.
	 */
	@PostMapping
	public ResponseEntity<Tema> postTema(@Valid @RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(tema));
	}

	/**
	 * Atualiza os dados de um tema existente.
	 *
	 * @param tema Os dados atualizados do tema.
	 * @return O tema atualizado ou um erro 404 caso não exista.
	 */
	@PutMapping
	public ResponseEntity<Tema> putTema(@Valid @RequestBody Tema tema){
		return temaRepository.findById(tema.getId())
				.map(resposta ->{
					return ResponseEntity.ok().body(temaRepository.save(tema));
				})
				.orElse(ResponseEntity.notFound().build());
	}

	/**
	 * Deleta um tema específico pelo seu ID.
	 *
	 * @param id O ID do tema a ser deletado.
	 * @return Uma resposta sem conteúdo, indicando a exclusão bem-sucedida, ou um erro 404 caso o tema não exista.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTema(@PathVariable Long id){
		return temaRepository.findById(id)
				.map(resposta -> {
					temaRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
}