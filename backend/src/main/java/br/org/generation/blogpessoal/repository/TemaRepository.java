package br.org.generation.blogpessoal.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.org.generation.blogpessoal.model.Tema;
/**
 * Representa o repositório de temas para o blog pessoal.
 * Esta interface estende o JpaRepository e fornece métodos de CRUD para a entidade Tema.
 */
@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {

	/**
	 * Busca temas que contêm a descrição fornecida, ignorando a capitalização.
	 *
	 * @param tema o termo de busca usado para filtrar temas pela descrição.
	 * @return uma lista de temas cuja descrição contém o termo de busca fornecido.
	 */
	List<Tema> findAllByDescricaoContainingIgnoreCase(String tema);

}
