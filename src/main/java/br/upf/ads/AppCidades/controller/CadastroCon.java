package br.upf.ads.AppCidades.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.upf.ads.AppCidades.dominio.Cidade;
import br.upf.ads.AppCidades.dominio.Pessoa;
import br.upf.ads.AppCidades.jpa.JPAUtil;
import br.upf.ads.AppCidades.jpa.JSFUtil;

@ManagedBean
@ViewScoped
public class CadastroCon implements Serializable {

	
	
	private List<Cidade> listaCidades;
	private Pessoa newUser;
	
	private Boolean voltar;
	
	private String nome;
	@NotBlank(message = "Informar o e-mail.")
	@Length(max = 100, message = "O e-mail deve ter até 100 caracteres.")
	@Email
	private String email;
	@NotBlank(message = "Informar a senha.")
	private String senha;
	@NotBlank(message = "Informe novamente a senha.")
	private String senha2;
	@NotNull(message = "Informar a cidade.")
	private Cidade cidade;
	
	
	public CadastroCon() {
		voltar = false;
		newUser = new Pessoa();
		carregarListaCidades();
	}
	
	public void salvar() {
		boolean erro = false;
		if (!senha.equals(senha2)) {
			erro = true;
			JSFUtil.messagemDeErro("Senhas não correspondem.");
		}
		if (erro)
			return;
		
		try {
			newUser.setNome(nome);
			newUser.setEmail(email);
			newUser.setSenha(LoginControle.getHashMd5(senha));
			newUser.setCidade(cidade);
			
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.merge(newUser);
			em.getTransaction().commit();
			em.close();
			voltar = true;
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.messagemDeErro("Ocorreu um erro ao cadastrar.");
		}
	}
	
	public void cancelar() throws IOException {
		JSFUtil.getResponse().sendRedirect("/AppCidades/faces/Login/LoginForm.xhtml");
	}
	
	public void carregarListaCidades() {
		EntityManager em = JPAUtil.getEntityManager();
		listaCidades = em.createQuery("from Cidade").getResultList();
		em.close();
	}
	
	// getters and setters =======================
	
	
	public Pessoa getNewUser() {
		return newUser;
	}

	public void setNewUser(Pessoa newUser) {
		this.newUser = newUser;
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

	public String getSenha2() {
		return senha2;
	}

	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public List<Cidade> getListaCidades() {
		return listaCidades;
	}

	public void setListaCidades(List<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}
	
	//==================================================
	
	
	public List<Cidade> completeCidade(String query) {
		EntityManager em = JPAUtil.getEntityManager();
		List<Cidade> results = em.createQuery(
		"from Cidade where upper(nome) like "+
		"'"+query.trim().toUpperCase()+"%' "+
		"order by nome").getResultList();
		em.close();
		return results;
	}

	public Boolean getVoltar() {
		return voltar;
	}

	public void setVoltar(Boolean voltar) {
		this.voltar = voltar;
	}
	
	//==================================================
	
}
