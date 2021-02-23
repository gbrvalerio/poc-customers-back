package com.gabrielvalerio.desafio.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.gabrielvalerio.desafio.validators.EnumValidator;

public class Phone {
	
	@NotBlank(message = "O campo é obrigatório.")
	@EnumValidator(enumClazz = PhoneType.class, message = "O tipo do telefone deve ser válido.")
	public String type;
	@NotBlank(message = "O campo é obrigatório.")
	@Pattern(regexp = "\\d{10,11}", message="Número de telefone inválido")
	public String number;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
}