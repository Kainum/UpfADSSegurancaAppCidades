package br.upf.ads.AppUpfDisciplinas.validacoes;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class StringOptionsValidImpl implements ConstraintValidator<StringOptionsValid, String> {
	
	private String[] options;
	
	public void initialize(StringOptionsValid a) {
		options = a.options();
	}

	public boolean isValid(String value, ConstraintValidatorContext cvc) {
		if (value == null)
			return true;
		List<String> list = Arrays.asList(options);
		
		return list.contains(value);
	}

}
