package br.upf.ads.AppCidades.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Pessoa implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PessoaId")
	@SequenceGenerator(name = "PessoaId", sequenceName = "PessoaId", allocationSize = 1)
	private Long id;
	@NotBlank(message = "Informar o nome.")
	@Length(min = 2, max = 60, message = "O nome deve ter entre {min} e {max} caracteres.")
	private String nome;
	@NotBlank(message = "Informar o e-mail.")
	@Length(max = 100, message = "O e-mail deve ter até 100 caracteres.")
	@Email
	@Column(unique = true, nullable = false)
	private String email;
	@NotBlank(message = "Informar a senha.")
	@Column(length = 32, nullable = false)
	private String senha;
	@Column(nullable = false)
	private Boolean admin;
	@NotNull(message = "Informar a cidade.")
	@ManyToOne(optional = false)
	private Cidade cidade;
	
	
	public Pessoa() {
		admin = false;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public Boolean getAdmin() {
		return admin;
	}


	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}


	public Cidade getCidade() {
		return cidade;
	}


	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
}
