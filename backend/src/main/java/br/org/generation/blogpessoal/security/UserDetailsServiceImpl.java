package br.org.generation.blogpessoal.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.org.generation.blogpessoal.model.Usuario;
import br.org.generation.blogpessoal.repository.UsuarioRepository;
/**
 * Implementação customizada do UserDetailsService para o blog pessoal.
 * Esta classe é responsável por localizar o usuário pelo nome de usuário e converter o usuário em um UserDetails.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;

	/**
	 * Localiza um usuário pelo nome de usuário e o converte em um UserDetails.
	 *
	 * @param userName Nome de usuário usado para localizar o usuário.
	 * @return um UserDetails que representa o usuário encontrado.
	 * @throws UsernameNotFoundException se o nome de usuário não for encontrado.
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<Usuario> usuario = userRepository.findByUsuario(userName);
		usuario.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));
		return usuario.map(UserDetailsImpl::new).get();
	}

}
