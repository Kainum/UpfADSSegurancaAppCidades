package br.upf.ads.AppCidades.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;

import br.upf.ads.AppCidades.dominio.Cidade;
import br.upf.ads.AppCidades.dominio.Pessoa;
import br.upf.ads.AppCidades.jpa.JPAUtil;
import br.upf.ads.AppCidades.jpa.JSFUtil;

@ManagedBean
@ViewScoped
public class PessoaCon implements Serializable {

	private Boolean editando;
	private List<Pessoa> lista;
	private Pessoa selecionado;
	
	private List<Cidade> listaCidades;
	
	@NotBlank(message = "Informar a senha.")
	private String senha;
	
	
	public PessoaCon() {
		try {
			checkAdmin();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setEditando(false);
		carregarLista();
		carregarListaCidades();
	}
	
	public void incluir() {
		editando = true;
		selecionado = new Pessoa();
	}
	
	public void alterar() {
		editando = true;
	}
	
	public void salvar() {
		try {
			selecionado.setSenha(LoginControle.getHashMd5(senha));
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.merge(selecionado);
			em.getTransaction().commit();
			em.close();
			editando = false;
			carregarLista();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.messagemDeErro("Ocorreu um erro ao salvar os dados.");
		}
	}
	
	public void excluir() {
		try {
			editando = false;
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.remove(em.merge(selecionado));
			em.getTransaction().commit();
			em.close();
			carregarLista();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.messagemDeErro("Erro ao excluir dados.");
		}
	}
	
	public void cancelar() {
		editando = false;
		selecionado = null;
	}
	
	public void carregarLista() {
		EntityManager em = JPAUtil.getEntityManager();
		lista = em.createQuery("from Pessoa").getResultList();
		em.close();
	}
	
	public void carregarListaCidades() {
		EntityManager em = JPAUtil.getEntityManager();
		listaCidades = em.createQuery("from Cidade").getResultList();
		em.close();
	}
	
	// getters and setters =======================
	
	public Boolean getEditando() {
		return editando;
	}
	public void setEditando(Boolean editando) {
		this.editando = editando;
	}
	public List<Pessoa> getLista() {
		return lista;
	}
	public void setLista(List<Pessoa> lista) {
		this.lista = lista;
	}
	public Pessoa getSelecionado() {
		return selecionado;
	}
	public void setSelecionado(Pessoa selecionado) {
		this.selecionado = selecionado;
	}
	
	public List<Cidade> getListaCidades() {
		return listaCidades;
	}

	public void setListaCidades(List<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
	
	//==================================================
	
	public void checkAdmin() throws IOException {
		HttpSession ses = JSFUtil.getSession();
		LoginControle lc = (LoginControle) ses.getAttribute("loginControle");
		if (lc == null || lc.getUsuarioLogado() == null || lc.getUsuarioLogado().getAdmin() == false) {
			JSFUtil.getResponse().sendRedirect("./Home.xhtml");
		}
	}
	
}
