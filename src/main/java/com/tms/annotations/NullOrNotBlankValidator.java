package com.tms.annotations;

import org.apache.commons.lang3.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, Object> {

	public void initialize(NullOrNotBlank parameters) {
	}

	public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
		return switch (value) {
		case null -> false;
		case Integer intValue -> intValue != null;
		case String strValue -> StringUtils.isNotEmpty(strValue);
		default -> false;
		};
	}
}
