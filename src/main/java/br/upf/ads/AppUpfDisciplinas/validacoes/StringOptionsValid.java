package br.upf.ads.AppUpfDisciplinas.validacoes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StringOptionsValidImpl.class)
@Documented
/**
 * 
 * Exemplo de uso
 * @StringOptionsValid(message = "Opção inválida", options = {"Fone", "Fax", "Celular"})
 */
public @interface StringOptionsValid {
	String message() default "{br.upf.ads.AppUpfDisciplinas.validacoes.StringOptionsValid}";
	Class <?>[] groups() default {};
	Class <? extends Payload>[] payload() default {};
	String[] options() default {};
}
