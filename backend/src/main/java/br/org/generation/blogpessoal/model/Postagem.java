package br.org.generation.blogpessoal.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Representa uma entidade de postagem para o blog pessoal.
 * Esta entidade está mapeada para uma tabela "tb_postagens" no banco de dados.
 */
@Entity
@Table(name="tb_postagens") // create table tb_postagens(
public class Postagem {

	/**
	 * Identificador único da postagem.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // id auto increment 
	private Long id;

	/**
	 * Título da postagem.
	 * É obrigatório e deve conter entre 5 a 100 caracteres.
	 */
	@NotBlank(message = "O artributo título é obrigatório!")
	@Size(min = 5, max = 100, message = "O atributo título deve conter no mínimo 5 e no máximo 100 caracteres!")
	private String titulo;

	/**
	 * Conteúdo da postagem.
	 * É obrigatório e deve conter entre 10 a 1000 caracteres.
	 */
	@NotBlank(message = "O artributo texto é obrigatório!")
	@Size(min = 10, max = 1000, message = "O atributo texto deve conter no mínimo 10 e no máximo 1000 caracteres!")
	private String texto;

	/**
	 * Data e hora da última atualização da postagem.
	 */
	@UpdateTimestamp
	private LocalDateTime data;

	/**
	 * Tema associado à postagem.
	 */
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;

	/**
	 * Usuário que fez a postagem.
	 */
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;

	/**
	 * @return o usuário que fez a postagem.
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Define o usuário que fez a postagem.
	 * @param usuario o usuário a ser definido.
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return o tema associado à postagem.
	 */
	public Tema getTema() {
		return tema;
	}

	/**
	 * Define o tema associado à postagem.
	 * @param tema o tema a ser definido.
	 */
	public void setTema(Tema tema) {
		this.tema = tema;
	}

	/**
	 * @return o identificador único da postagem.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Define o identificador único da postagem.
	 * @param id o identificador a ser definido.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return o título da postagem.
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Define o título da postagem.
	 * @param titulo o título a ser definido.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return o conteúdo da postagem.
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * Define o conteúdo da postagem.
	 * @param texto o conteúdo a ser definido.
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * @return a data e hora da última atualização da postagem.
	 */
	public LocalDateTime getData() {
		return data;
	}

	/**
	 * Define a data e hora da última atualização da postagem.
	 * @param data a data e hora a serem definidas.
	 */
	public void setData(LocalDateTime data) {
		this.data = data;
	}

}