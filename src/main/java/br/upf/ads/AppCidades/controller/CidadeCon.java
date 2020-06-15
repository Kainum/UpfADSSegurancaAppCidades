package br.upf.ads.AppCidades.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.upf.ads.AppCidades.dominio.Cidade;
import br.upf.ads.AppCidades.dominio.Estado;
import br.upf.ads.AppCidades.dominio.Pessoa;
import br.upf.ads.AppCidades.jpa.JPAUtil;
import br.upf.ads.AppCidades.jpa.JSFUtil;

@ManagedBean
@ViewScoped
public class CidadeCon implements Serializable {

	private Boolean editando;
	private List<Cidade> lista;
	private Cidade selecionado;
	
	private List<Estado> listaEstados;
	
	
	public CidadeCon() {
		setEditando(false);
		carregarLista();
		carregarListaEstados();
	}
	
	public void incluir() {
		editando = true;
		selecionado = new Cidade();
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
		lista = em.createQuery("from Cidade").getResultList();
		em.close();
	}
	
	public void carregarListaEstados() {
		EntityManager em = JPAUtil.getEntityManager();
		listaEstados = em.createQuery("from Estado").getResultList();
		em.close();
	}
	
	// getters and setters =======================
	
	public Boolean getEditando() {
		return editando;
	}
	public void setEditando(Boolean editando) {
		this.editando = editando;
	}
	public List<Cidade> getLista() {
		return lista;
	}
	public void setLista(List<Cidade> lista) {
		this.lista = lista;
	}
	public Cidade getSelecionado() {
		return selecionado;
	}
	public void setSelecionado(Cidade selecionado) {
		this.selecionado = selecionado;
	}
	
	public List<Estado> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}
	
	//==================================================
	
	
	public List<Estado> completeEstado(String query) {
		EntityManager em = JPAUtil.getEntityManager();
		List<Estado> results = em.createQuery(
		"from Estado where upper(nome) like "+
		"'"+query.trim().toUpperCase()+"%' "+
		"order by nome").getResultList();
		em.close();
		return results;
	}
	
}
