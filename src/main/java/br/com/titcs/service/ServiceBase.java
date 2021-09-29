package br.com.titcs.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.util.CollectionUtils;

import br.com.titcs.domain.DomainBase;
import br.com.titcs.exceptions.CustomRuntimeException;

public interface ServiceBase {

	/**
	 * Método para efetuar validação das entradas das requisições
	 * 
	 * @param domain - Domain
	 * @throws RequestValidatorException - RequestValidatorException
	 */
	default void validateRequest(DomainBase domain) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<DomainBase>> violations = validator.validate(domain);

		if (!CollectionUtils.isEmpty(violations)) {

			StringBuilder error = new StringBuilder();
			for (ConstraintViolation<DomainBase> violation : violations) {
				error.append(violation.getMessage()).append(";");
				
			}
			throw new CustomRuntimeException(error.toString());
		}
	}
}
