package br.upf.ads.AppCidades.jpa;

import javax.persistence.Persistence;

public class CriarBase {

	public CriarBase() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Persistence.createEntityManagerFactory("AppCidadesPU");

	}

}
