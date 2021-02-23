package com.gabrielvalerio.desafio.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.gabrielvalerio.desafio.validators.EnumValidator;

public class Address {
	
	@NotBlank(message = "O campo 'address.cep' é obrigatório.")
	@Size(min = 8, max = 8, message = "O campo 'address.cep' deve conter obrigatoriamente 8 caracteres.")
	@Pattern(regexp = "\\d{8}", message = "CEP inválido.")
	public String cep;
	@NotBlank(message = "O campo 'address.address' é obrigatório.")
	public String address;
	@NotBlank(message = "O campo 'address.neighbourhood' é obrigatório.")
	public String neighbourhood;
	@NotBlank(message = "O campo 'address.city' é obrigatório.")
	public String city;
	@NotBlank(message = "O campo 'address.uf' é obrigatório.")
	@Size(min = 2, max = 2, message = "O campo 'address.uf' deve conter obrigatoriamente 2 caracteres.")
	@EnumValidator(enumClazz = FederationUnit.class, message = "O UF deve ser válido e de uma unidade da federação.")
	public String uf;
	public String complement;
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNeighbourhood() {
		return neighbourhood;
	}
	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	
}