package br.upf.ads.AppCidades.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.upf.ads.AppCidades.dominio.Estado;
import br.upf.ads.AppCidades.jpa.JPAUtil;

@FacesConverter(value = "estadoConverter")
public class EstadoConverter implements Converter {
	
	@Override
	public Estado getAsObject(FacesContext fc, UIComponent uic, String value) {
		
		if (value != null && value.trim().length() > 0) {
			try {
				EntityManager em = JPAUtil.getEntityManager();
				Estado ret = em.find(Estado.class, Long.parseLong(value));
				em.close();
				return ret;
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Conversão de EstadoSemestre", "EstadoSemestre inválido."));
			}
		} else
			return null;
	}
	
	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((Estado) object).getId());
		} else
			return null;
	}
}
