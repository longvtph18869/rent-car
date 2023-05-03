package com.example.rent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.rent.entities.User;
import com.example.rent.repositories.UserRepository;
@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Account " + username + " was not found in the database");
		}
		return new MyUserDetails(user);
	}
	
	@Transactional
	public UserDetails loadUserById(Integer id) {
	    User user = userRepository.findById(id).orElseThrow(
	        () -> new UsernameNotFoundException("User not found with id : " + id)
	    );
	    return new MyUserDetails(user);
	}
}
