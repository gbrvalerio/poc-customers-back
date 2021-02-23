package com.gabrielvalerio.desafio.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
	
	public String username;
	@JsonIgnore
	public String password;
	public List<String> roles;
	
	public User(String username, String password, List<String> roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	
	
	
}