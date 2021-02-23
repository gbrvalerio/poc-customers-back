package com.gabrielvalerio.desafio.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gabrielvalerio.desafio.validators.CPFValidator;
import com.gabrielvalerio.desafio.validators.EmailListValidator;

@Document(collection = "customers")
public class Customer {

	@Id
	public String id;

	@NotBlank(message = "O campo 'name' é obrigatório.")
	@Size(min = 3, max = 100, message = "O campo 'name' deve conter entre 3 e 100 caracteres.")
	public String name;
	@NotBlank(message = "O campo 'cpf' é obrigatório.")
	@Size(min = 11, max = 11, message = "O campo 'cpf' deve conter obrigatoriamente 11 caracteres.")
	@CPFValidator
	public String cpf;
	@NotNull(message = "O campo 'address' é obrigatório.")
	@Valid
	public Address address;
	@Size(min = 1, message = "É obrigatório o cadastro de ao menos um número de contato.")
	@NotNull(message = "O campo 'phones' é obrigatório.")
	@Valid
	public List<Phone> phones;
	@Size(min = 1, message = "É obrigatório o cadastro de ao menos um email.")
	@NotNull(message = "O campo 'emails' é obrigatório.")
	@EmailListValidator
	public List<String> emails;
	
	public Date createdAt;
	public Date updatedAt;

	public Customer() {}

	@Override
	public String toString() {
		return String.format(
				"Customer[id=%s, name='%s', cpf='%s']",
				id, name, cpf);
	}
	
	@JsonProperty("address")
    private void unpackNested(Map<String,Object> address) {
        this.address = new Address();
        this.address.address = (String) address.get("address");
		this.address.cep = (String) address.get("cep");
		this.address.uf = (String) address.get("uf");
		this.address.city = (String) address.get("city");
		this.address.neighbourhood = (String) address.get("neighbourhood");
		this.address.complement = (String) address.get("complement");
    }

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}