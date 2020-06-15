package br.upf.ads.AppCidades.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Cidade implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CidadeId")
	@SequenceGenerator(name = "CidadeId", sequenceName = "CidadeId", allocationSize = 1)
	private Long id;
	@NotBlank(message = "Informar o nome.")
	@Length(min = 2, max = 60, message = "O nome deve ter entre {min} e {max} caracteres.")
	private String nome;
	@NotNull(message = "Informar o estado.")
	@ManyToOne(optional = false)
	private Estado estado;
	
	public Cidade() {
		// TODO Auto-generated constructor stub
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return nome;
	}
	
}
