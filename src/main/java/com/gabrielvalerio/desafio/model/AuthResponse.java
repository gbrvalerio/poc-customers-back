package com.gabrielvalerio.desafio.model;

import java.io.Serializable;

public class AuthResponse implements Serializable {

	/**
	 * generated
	 */
	private static final long serialVersionUID = -4507019254410171351L;
	private final String token;
	private final User user;

	public AuthResponse(String token, User user) {
		this.token = token;
		this.user = user;
	}

	public String getToken() {
		return this.token;
	}
	
	public User getUser() {
		return this.user;
	}

}