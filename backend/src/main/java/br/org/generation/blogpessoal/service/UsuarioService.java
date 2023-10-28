package br.org.generation.blogpessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.org.generation.blogpessoal.model.Usuario;
import br.org.generation.blogpessoal.model.UsuarioLogin;
import br.org.generation.blogpessoal.repository.UsuarioRepository;

/**
 * Classe de serviço para o gerenciamento de usuários.
 * Esta classe fornece operações relacionadas à criação, atualização e autenticação de usuários.
 */
@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;


	/**
	 * Cadastra um novo usuário no sistema.
	 *
	 * @param usuario Objeto contendo os dados do usuário a ser cadastrado.
	 * @return um Optional contendo o usuário cadastrado ou vazio, se o nome de usuário já existir.
	 */
	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			return Optional.empty();

		usuario.setSenha(criptografarSenha(usuario.getSenha()));
		return Optional.of(usuarioRepository.save(usuario));

	}

	/**
	 * Atualiza os dados de um usuário existente.
	 *
	 * @param usuario Objeto contendo os dados atualizados do usuário.
	 * @return um Optional contendo o usuário atualizado ou vazio, se o usuário não for encontrado.
	 */
	public Optional<Usuario> atualizarUsuario(Usuario usuario) {

		if (usuarioRepository.findById(usuario.getId()).isPresent()) {

			Optional<Usuario> buscaUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());

			if ((buscaUsuario.isPresent()) && (buscaUsuario.get().getId() != usuario.getId()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

			usuario.setSenha(criptografarSenha(usuario.getSenha()));

			return Optional.ofNullable(usuarioRepository.save(usuario));

		}

		return Optional.empty();

	}

	/**
	 * Autentica um usuário com base no nome de usuário e senha.
	 *
	 * @param usuarioLogin Objeto contendo o nome de usuário e senha para autenticação.
	 * @return um Optional contendo os detalhes do usuário logado e o token ou vazio, se a autenticação falhar.
	 */
	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {

		Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());

		if (usuario.isPresent()) {
			if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {

				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setFoto(usuario.get().getFoto());
				usuarioLogin.get()
						.setToken(gerarBasicToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setSenha(usuario.get().getSenha());

				return usuarioLogin;

			}
		}

		return Optional.empty();

	}

	/**
	 * Criptografa a senha do usuário utilizando o algoritmo BCrypt.
	 *
	 * @param senha Senha original a ser criptografada.
	 * @return Senha criptografada.
	 */
	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.encode(senha);

	}

	/**
	 * Compara uma senha fornecida (geralmente digitada pelo usuário) com uma senha criptografada
	 * armazenada no banco de dados.
	 *
	 * @param senhaDigitada Senha fornecida pelo usuário.
	 * @param senhaBanco Senha criptografada armazenada no banco de dados.
	 * @return Verdadeiro se as senhas corresponderem, falso caso contrário.
	 */
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.matches(senhaDigitada, senhaBanco);

	}

	/**
	 * Gera um token Basic Auth baseado em um nome de usuário e senha.
	 * O token é uma codificação base64 da combinação do nome de usuário e senha.
	 *
	 * @param usuario Nome de usuário.
	 * @param senha Senha do usuário.
	 * @return Token Basic Auth.
	 */
	private String gerarBasicToken(String usuario, String senha) {

		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);

	}

}