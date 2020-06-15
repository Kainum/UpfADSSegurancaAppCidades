package br.upf.ads.AppCidades.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory factory = null;    
	
	public static EntityManager getEntityManager() {
		if (factory == null)            
			factory = Persistence.createEntityManagerFactory("AppCidadesPU");        
		
		return factory.createEntityManager();
	}
}
