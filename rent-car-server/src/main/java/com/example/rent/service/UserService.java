package com.example.rent.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.rent.DTO.UserDTO;
import com.example.rent.entities.User;
import com.example.rent.repositories.DrivingLicenseRepository;
import com.example.rent.repositories.UserRepository;
import com.example.rent.utils.Constants;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DrivingLicenseRepository drivingLicenseRepository;

	public User registeUser(UserDTO userDTO) {
		User user = new User();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		user.setUsername(userDTO.getUsername());
		user.setFullName(userDTO.getFullName());
		user.setPassword(encoder.encode(userDTO.getPassword()));
		user.setGender(true);
		user.setStatus(true);
		user.setRole(Constants.ROLE_CUSTOMER);
		user.setJoinDate(LocalDateTime.now());
		userRepository.save(user);

		return user;

	}
	
	public boolean checkUserNameWasUsed(String username) {
		int countUser = userRepository.countUser(username);
		if (countUser > 0) {
			return true;
		}
		return false;
	}

	public UserDTO getByUserName(String username) {
		UserDTO dto;
		User user = userRepository.findByUsername(username);

		if (user != null) {
			dto = new UserDTO();
			/* Set all the values */
			dto.setId(user.getId());
			dto.setAvatar(user.getAvatar());
			dto.setUsername(user.getUsername());
			dto.setFullName(user.getFullName());
			dto.setDateOfBirth(user.getDateOfBirth());
			dto.setGender(user.isGender());
			dto.setJoinDate(user.getJoinDate());
			dto.setPhoneNumber(user.getPhoneNumber());
			dto.setAddress(user.getAddress());
			dto.setRole(user.getRole());
			dto.setStatus(user.isStatus());
			return dto;
		}
		return null;
	}
	
    public boolean changePassword(String username, String currentPassword, String newPassword) {
        User user = userRepository.findByUsername(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        if (user == null) {
            return false;
        }

        if (!encoder.matches(currentPassword, user.getPassword())) {
            return false;
        }

        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
        return true;
    }
}
