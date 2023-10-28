package br.org.generation.blogpessoal.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.org.generation.blogpessoal.model.Usuario;
/**
 * Implementação customizada do UserDetails para o blog pessoal.
 * Esta classe fornece detalhes sobre o usuário que está sendo autenticado, como nome de usuário, senha e autoridades.
 */
public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private List<GrantedAuthority> authorities;

	/**
	 * Construtor que inicializa o objeto UserDetailsImpl com os dados de um usuário.
	 *
	 * @param usuario Objeto do tipo Usuario do qual os detalhes serão extraídos.
	 */
	public UserDetailsImpl(Usuario usuario) {
		this.userName = usuario.getUsuario();
		this.password = usuario.getSenha();
	}

	/**
	 * Construtor padrão.
	 */
	public UserDetailsImpl() {
	}

	/**
	 * @return uma coleção das autoridades concedidas ao usuário.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * @return a senha do usuário.
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * @return o nome de usuário (geralmente é o email).
	 */
	@Override
	public String getUsername() {
		return userName;
	}

	/**
	 * @return indica se a conta do usuário não expirou.
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * @return indica se a conta do usuário não está bloqueada.
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * @return indica se as credenciais do usuário não expiraram.
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * @return indica se o usuário está habilitado.
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
}