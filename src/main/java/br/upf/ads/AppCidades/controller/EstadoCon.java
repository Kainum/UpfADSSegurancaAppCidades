package br.upf.ads.AppCidades.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.upf.ads.AppCidades.dominio.Estado;
import br.upf.ads.AppCidades.jpa.JPAUtil;
import br.upf.ads.AppCidades.jpa.JSFUtil;

@ManagedBean
@ViewScoped
public class EstadoCon implements Serializable {

	private Boolean editando;
	private List<Estado> lista;
	private Estado selecionado;
	
	
	public EstadoCon() {
		setEditando(false);
		carregarLista();
	}
	
	public void incluir() {
		editando = true;
		selecionado = new Estado();
	}
	
	public void alterar() {
		editando = true;
	}
	
	public void salvar() {
		try {
			editando = false;
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			em.merge(selecionado);
			em.getTransaction().commit();
			em.close();
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
		lista = em.createQuery("from Estado").getResultList();
		em.close();
	}
	
	// getters and setters =======================
	
	public Boolean getEditando() {
		return editando;
	}
	public void setEditando(Boolean editando) {
		this.editando = editando;
	}
	public List<Estado> getLista() {
		return lista;
	}
	public void setLista(List<Estado> lista) {
		this.lista = lista;
	}
	public Estado getSelecionado() {
		return selecionado;
	}
	public void setSelecionado(Estado selecionado) {
		this.selecionado = selecionado;
	}
	
	//==================================================
	
}
