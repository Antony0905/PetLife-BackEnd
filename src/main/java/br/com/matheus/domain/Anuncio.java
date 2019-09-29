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

	@NotNull(message = "Campo Titulo não pode ser Vazio!")
	@NotEmpty
	private String titulo;

	@NotNull(message = "Campo Descrição não pode ser Vazio!")
	@NotEmpty
	private String descricao;

	@NotNull(message = "Campo CEP não pode ser Vazio!")
	@NotEmpty
	private String cep;

	@NotNull(message = "Campo Logradouro não pode ser Vazio!")
	@NotEmpty
	private String logradouro;

	@NotEmpty
	private String complemento;

	@NotNull(message = "Campo Bairro não pode ser Vazio!")
	@NotEmpty
	private String bairro;

	@NotNull(message = "Campo Cidade não pode ser Vazio!")
	@NotEmpty
	private String cidade;

	@NotNull(message = "Campo Estado não pode ser Vazio!")
	@NotEmpty
	private String estado;

	@NotNull(message = "Campo Numero não pode ser Vazio!")
	@NotEmpty
	private String numero;
	private Date dataCadastro;

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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
