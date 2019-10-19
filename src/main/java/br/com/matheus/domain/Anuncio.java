package br.com.matheus.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table
public class Anuncio implements Serializable {

	private static final long serialVersionUID = 1L;

	public Anuncio() {
		this.dataCadastro = new Date();
	}

	@Id
	@GeneratedValue(generator = "anuncio_generator")
	@SequenceGenerator(name = "anuncio_generator", sequenceName = "anuncio_generator", initialValue = 1, allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	private Integer userId;

	@NotNull(message = "Campo Titulo não pode ser Vazio!")
	@NotEmpty
	private String titulo;

	@NotNull(message = "Campo Descrição não pode ser Vazio!")
	@NotEmpty
	private String descricao;

	private Date dataCadastro;

	private Double preco;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
