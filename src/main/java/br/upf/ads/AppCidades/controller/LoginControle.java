package br.upf.ads.AppCidades.controller;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.upf.ads.AppCidades.dominio.Pessoa;
import br.upf.ads.AppCidades.jpa.JPAUtil;

@ManagedBean
@SessionScoped
public class LoginControle implements Serializable {

	private String email;
	private String senha;
	private Pessoa usuarioLogado;
	
	public String cadastrar() {
		return "/faces/Login/CadastroForm.xhtml";
	}
	
	public String entrar() {
		EntityManager em = JPAUtil.getEntityManager();
		Query qry = em.createQuery("from Pessoa where email=:email and senha=:senha");
		qry.setParameter("email", email);
		qry.setParameter("senha", getHashMd5(senha));
		usuarioLogado = null;
		try {
			usuarioLogado = (Pessoa) qry.getSingleResult();
		}catch(Exception e) {
			
		}
		if (usuarioLogado != null) {
			// autenticou corretamente. Redireciona para a tela principal da aplicação
			return "/faces/App/Home.xhtml?faces-redirect=true";
		} else {
			// mostrar mensagem e ficar na tela de login.
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha inválidos.", "");
			FacesContext.getCurrentInstance().addMessage("", msg);
			return "";
		}
		
	}
	
	public String sair() {  
	    usuarioLogado = null; 
	    FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO,  
	                                                       "Usuário Desconectado!" , ""); 
	          FacesContext.getCurrentInstance().addMessage(null, mensagem);        
	    return "/faces/Login/LoginForm.xhtml"; 
	}
	
	public LoginControle() {
		super();
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

	public Pessoa getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Pessoa usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	//=====================================================
	
	public static String getHashMd5(String value) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        BigInteger hash = new BigInteger(1, md.digest(value.getBytes()));
        return hash.toString(16);
    }
}
