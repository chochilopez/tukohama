package com.gloit.tukohama.security.model;

import java.util.Set;

public class AuthenticationResponse {

	private String token;
	private String username;
	private Set<String> roles;
	
	public AuthenticationResponse(){}

	public AuthenticationResponse(String token, String username, Set<String> roles) {
		super();
		this.token = token;
		this.username=username;
		this.roles=roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
}
