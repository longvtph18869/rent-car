package com.example.rent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rent.DTO.UserDTO;
import com.example.rent.auth.AuthenticationRequest;
import com.example.rent.auth.AuthenticationResponse;
import com.example.rent.config.MyUserDetails;
import com.example.rent.config.MyUserDetailsService;
import com.example.rent.service.UserService;
import com.example.rent.token.JwtTokenProvider;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		final MyUserDetails userDetails = (MyUserDetails) myUserDetailsService
				.loadUserByUsername(authRequest.getUsername());

		final String jwt = tokenProvider.generateToken(userDetails);
		return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(jwt));
	}
	
//	@PostMapping(value = "")
//	public ResponseEntity<UserDTO> saveOne(@RequestBody UserDTO dto) {
//		UserDTO result = userService.saveOne(dto);
//
//		return new ResponseEntity<>(result, result != null? HttpStatus.OK : HttpStatus.BAD_REQUEST);
//	}
	
	@GetMapping()
	public ResponseEntity<UserDTO> getOne(@RequestParam String username) {
		UserDTO result = userService.getByUserName(username);
		return new ResponseEntity<>(result, result != null? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
}
