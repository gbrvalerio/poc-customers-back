package com.gabrielvalerio.desafio.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

@Documented
@Constraint(validatedBy = EmailListValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@ReportAsSingleViolation
public @interface EmailListValidator {

  String message() default "Email inválido";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}