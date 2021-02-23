package com.gabrielvalerio.desafio.validators;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailListValidatorImpl implements ConstraintValidator<EmailListValidator, List<String>> {

	private static final String owaspRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

	@Override
	public boolean isValid(List<String> emails, ConstraintValidatorContext context) {
		if(emails == null) return false;
		
		Pattern pattern = Pattern.compile(owaspRegex);
		for (String value : emails) {
			Matcher matcher = pattern.matcher(value);
			if(!matcher.matches()) {
				return false;
			}
		}
		return true;
	}

}
