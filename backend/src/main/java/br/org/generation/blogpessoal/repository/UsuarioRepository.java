package br.org.generation.blogpessoal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.blogpessoal.model.Usuario;
/**
 * Representa o repositório de usuários para o blog pessoal.
 * Esta interface estende o JpaRepository e fornece métodos de CRUD e consultas personalizadas para a entidade Usuario.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	/**
	 * Busca um usuário pelo seu nome de usuário.
	 *
	 * @param usuario o nome de usuário usado para localizar a entidade.
	 * @return um Optional contendo o usuário, se encontrado.
	 */
	public Optional<Usuario> findByUsuario(String usuario);

	/**
	 * Busca usuários que contêm o nome fornecido, ignorando a capitalização.
	 *
	 * @param nome o termo de busca usado para filtrar usuários pelo nome.
	 * @return uma lista de usuários cujo nome contém o termo de busca fornecido.
	 */
	public List<Usuario> findAllByNomeContainingIgnoreCase(String nome);

	/**
	 * Busca um usuário pelo seu identificador único.
	 *
	 * @param id o identificador único do usuário.
	 * @return um Optional contendo o usuário, se encontrado.
	 */
	public Optional<Usuario> findById(Long id);
}