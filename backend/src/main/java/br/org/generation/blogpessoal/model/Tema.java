package br.org.generation.blogpessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Representa uma entidade de tema para o blog pessoal.
 * Esta entidade está mapeada para uma tabela "tb_temas" no banco de dados.
 */
@Entity
@Table(name="tb_temas") 
public class Tema {

	/**
	 * Identificador único do tema.
	 */
	@Id // primary key (id)
	@GeneratedValue(strategy = GenerationType.IDENTITY) // id auto increment 
	private Long id;

	/**
	 * Descrição do tema.
	 * É obrigatório e não pode conter apenas espaços em branco.
	 */
	@NotBlank(message = "O atributo Descrição é obrigatório e não pode conter espaços em branco.")
	private String descricao;

	/**
	 * Lista de postagens associadas ao tema.
	 */
	@OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("tema")
	private List<Postagem> postagem;

	/**
	 * @return o identificador único do tema.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Define o identificador único do tema.
	 * @param id o identificador a ser definido.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return a descrição do tema.
	 */
	public String getDescricao() {
		return descricao;
	}


	/**
	 * Define a descrição do tema.
	 * @param descricao a descrição a ser definida.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return a lista de postagens associadas ao tema.
	 */
	public List<Postagem> getPostagem() {
		return postagem;
	}


	/**
	 * Define a lista de postagens associadas ao tema.
	 * @param postagem a lista de postagens a ser definida.
	 */
	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
	
	

}