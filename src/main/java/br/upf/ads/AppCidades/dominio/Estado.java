package br.upf.ads.AppCidades.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
public class Estado implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EstadoId")
	@SequenceGenerator(name = "EstadoId", sequenceName = "EstadoId", allocationSize = 1)
	private Long id;
	@NotBlank(message = "Informar o nome.")
	@Length(min = 2, max = 60, message = "O nome deve ter entre {min} e {max} caracteres.")
	private String nome;
	@NotBlank(message = "Informar a UF.")
	@Length(max = 2, message = "A UF deve ter até {max} caracteres.")
	private String uf;
	
	public Estado() {
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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	@Override
	public String toString() {
		return nome;
	}
}
