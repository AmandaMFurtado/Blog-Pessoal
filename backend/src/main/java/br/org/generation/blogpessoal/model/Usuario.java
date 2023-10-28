package br.org.generation.blogpessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * Representa uma entidade de usuário para o blog pessoal.
 * Esta entidade está mapeada para uma tabela "tb_usuarios" no banco de dados.
 */
@Entity
@Table(name="tb_usuarios")
public class Usuario {

	/**
	 * Identificador único do usuário.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;

	/**
	 * Nome do usuário.
	 * É obrigatório.
	 */
	@NotNull(message = "O atributo Nome é obrigatório!")
	private String nome;

	/**
	 * Endereço de email do usuário.
	 * É obrigatório e deve ser um email válido.
	 */
	@Schema(example = "email@email.com")
	@NotNull(message = "O atributo Usuário é obrigatório!")
	@Email(message = "O atributo Usuaário deve ser um email válido!")
	private String usuario;

	/**
	 * Senha de acesso do usuário.
	 * É obrigatório e deve ter no mínimo 8 caracteres.
	 */
	@NotBlank(message="O atributo Senha é obrigatório!")
	@Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres")
	private String senha;

	/**
	 * Foto de perfil do usuário.
	 */
	private String foto;

	/**
	 * Lista de postagens associadas ao usuário.
	 */
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Postagem> postagem;

	/**
	 * Construtor com todos os atributos.
	 *
	 * @param id Identificador único do usuário.
	 * @param nome Nome do usuário.
	 * @param usuario Endereço de email do usuário.
	 * @param senha Senha de acesso do usuário.
	 * @param foto Foto de perfil do usuário.
	 */
	public Usuario(Long id, String nome, String usuario, String senha, String foto) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.foto = foto;
	}

	/**
	 * Construtor padrão.
	 */
	public Usuario() {}

	/**
	 * @return o identificador único do usuário.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Define o identificador único do usuário.
	 * @param id o identificador a ser definido.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return o nome do usuário.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Define o nome do usuário.
	 * @param nome o nome a ser definido.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return o endereço de email do usuário.
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Define o endereço de email do usuário.
	 * @param usuario o email a ser definido.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return a senha de acesso do usuário.
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Define a senha de acesso do usuário.
	 * @param senha a senha a ser definida.
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return a foto de perfil do usuário.
	 */
	public String getFoto() {
		return foto;
	}

	/**
	 * Define a foto de perfil do usuário.
	 * @param foto a foto a ser definida.
	 */
	public void setFoto(String foto) {
		this.foto = foto;
	}

	/**
	 * @return a lista de postagens associadas ao usuário.
	 */
	public List<Postagem> getPostagem() {
		return postagem;
	}

	/**
	 * Define a lista de postagens associadas ao usuário.
	 * @param postagem a lista de postagens a ser definida.
	 */
	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
}