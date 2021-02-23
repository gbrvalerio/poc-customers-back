package com.gabrielvalerio.desafio.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Size;

@Documented
@Constraint(validatedBy = CPFValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Size(min = 11, max = 11, message = "O campo deve conter obrigatoriamente 11 caracteres.")
@ReportAsSingleViolation
public @interface CPFValidator {

  String message() default "CPF inválido";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}