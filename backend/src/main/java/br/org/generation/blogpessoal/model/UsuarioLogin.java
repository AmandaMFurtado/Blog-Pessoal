package br.org.generation.blogpessoal.model;
/**
 * Representa uma entidade de login para o blog pessoal.
 * Esta classe é utilizada para autenticação e transmissão de informações do usuário durante o processo de login.
 */
public class UsuarioLogin {

	/**
	 * Identificador único do usuário.
	 */
	private Long id;

	/**
	 * Nome do usuário.
	 */
	private String nome;

	/**
	 * Endereço de email ou nome de usuário para autenticação.
	 */
	private String usuario;

	/**
	 * Senha de acesso do usuário.
	 */
	private String senha;

	/**
	 * Foto de perfil do usuário.
	 */
	private String foto;

	/**
	 * Token JWT para autenticação após o login bem-sucedido.
	 */
	private String token;

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
	 * @return o endereço de email ou nome de usuário para autenticação.
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Define o endereço de email ou nome de usuário para autenticação.
	 * @param usuario o nome/emails de usuário a ser definido.
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
	 * @return o token JWT para autenticação.
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Define o token JWT para autenticação.
	 * @param token o token a ser definido.
	 */
	public void setToken(String token) {
		this.token = token;
	}
}