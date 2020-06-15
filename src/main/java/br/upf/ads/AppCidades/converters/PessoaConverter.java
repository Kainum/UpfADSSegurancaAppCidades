package br.upf.ads.AppCidades.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.upf.ads.AppCidades.dominio.Pessoa;
import br.upf.ads.AppCidades.jpa.JPAUtil;

@FacesConverter(value = "pessoaConverter")
public class PessoaConverter implements Converter {
	
	@Override
	public Pessoa getAsObject(FacesContext fc, UIComponent uic, String value) {
		
		if (value != null && value.trim().length() > 0) {
			try {
				EntityManager em = JPAUtil.getEntityManager();
				Pessoa ret = em.find(Pessoa.class, Long.parseLong(value));
				em.close();
				return ret;
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Conversão de Pessoa", "Pessoa inválido."));
			}
		} else
			return null;
	}
	
	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((Pessoa) object).getId());
		} else
			return null;
	}
}
