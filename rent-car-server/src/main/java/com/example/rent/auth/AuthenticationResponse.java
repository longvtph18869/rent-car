package com.example.rent.auth;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {
	private String accessToken;
	private String tokenType = "Bearer";

	public AuthenticationResponse(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

}
