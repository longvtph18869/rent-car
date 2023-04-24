package com.example.rent.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.example.rent.entities.User;
import com.example.rent.service.UserService;
import com.example.rent.token.JwtTokenProvider;
import com.example.rent.utils.ChangePasswordRequest;

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

	@PostMapping("/registerUser")
	public ResponseEntity<User> saveOne(@RequestBody UserDTO userDTO) {
		try {
			User savedUser = userService.registeUser(userDTO);
			return ResponseEntity.ok().body(savedUser);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, @RequestParam String username) {
		boolean result = userService.changePassword(username, request.getCurrentPassword(), request.getNewPassword());
        if (result) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
	}

	@PostMapping("/check/usernameWasUsed")
	public Boolean usernameWasUsed(@RequestBody UserDTO userDTO) {
		boolean result = true;
		if (userDTO.getUsername() != null && StringUtils.hasText(userDTO.getUsername()))
			result = userService.checkUserNameWasUsed(userDTO.getUsername());
		return result;
	}

	@GetMapping("/{username}")
	public ResponseEntity<UserDTO> getOne(@PathVariable String username) {
		UserDTO result = userService.getByUserName(username);
		return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

}
