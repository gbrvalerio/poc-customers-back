package com.gabrielvalerio.desafio.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.gabrielvalerio.desafio.validators.EnumValidator;

@Document(collection = "audit")
public class AuditLog {

	@Id
	public String id;

	@NotBlank(message = "O campo é obrigatório.")
	@EnumValidator(enumClazz = AuditOperation.class, message = "Selecione uma operação válida.")
	public String operation;
	
	@NotBlank(message = "O campo é obrigatório.")
	public String collection;
	
	@NotBlank(message = "O campo é obrigatório.")
	public Object body;
	
	@NotBlank(message = "O campo é obrigatório.")
	public Object username;
	
	public Date createdAt;

	public AuditLog(String operation, String collection, Object body, String username) {
		super();
		this.operation = operation;
		this.collection = collection;
		this.body = body;
		this.username = username;
		this.createdAt = new Date();
	}
	
	
	
}