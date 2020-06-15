package br.upf.ads.AppCidades.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.primefaces.PrimeFaces;

@ManagedBean
@ViewScoped
public class HomeCon_Backup implements Serializable{
	
	public HomeCon_Backup() {
		super();
	}
	
	public void abrirPopup(String url, int largura, int altura, boolean modal) {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", modal);
		options.put("width", largura);
		options.put("height", altura);
		options.put("contentWidth", "100%");
		options.put("contentHeigh", "100%");
		options.put("headerElement", "customheader");
		options.put("resizable", false);
		options.put("minimizable", true);
		options.put("maximizable", true);
		PrimeFaces.current().dialog().openDynamic(url, options, null);
	}
	
}
