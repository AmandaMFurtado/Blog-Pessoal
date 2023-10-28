package br.org.generation.blogpessoal.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.blogpessoal.model.Postagem;
/**
 * Representa o repositório de postagens para o blog pessoal.
 * Esta interface estende o JpaRepository e fornece métodos de CRUD para a entidade Postagem.
 */
@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

	/**
	 * Busca postagens que contêm o título fornecido, ignorando a capitalização.
	 * Esta consulta é equivalente a: "SELECT * FROM tb_postagens WHERE title LIKE '%title%'".
	 *
	 * @param titulo o termo de busca usado para filtrar postagens pelo título.
	 * @return uma lista de postagens cujo título contém o termo de busca fornecido.
	 */
	List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);
}