package com.example.rent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rent.auth.AuthenticationRequest;
import com.example.rent.auth.AuthenticationResponse;
import com.example.rent.config.MyUserDetails;
import com.example.rent.config.MyUserDetailsService;
import com.example.rent.token.JwtTokenProvider;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (Exception e) {
			return ResponseEntity.ok("Account or password incorrect");
		}

		final MyUserDetails userDetails = (MyUserDetails) myUserDetailsService
				.loadUserByUsername(authRequest.getUsername());

		final String jwt = tokenProvider.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
