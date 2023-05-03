package com.example.rent.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.rent.DTO.UserDTO;
import com.example.rent.entities.User;
import com.example.rent.repositories.UserRepository;
import com.example.rent.utils.Constants;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	public UserDTO findById(int id) {
		UserDTO dto;
		User user = userRepository.findById(id);
		if (user != null) {
			dto = new UserDTO();
			dto.setId(user.getId());
			dto.setAvatar(user.getAvatar());
			dto.setFullName(user.getFullName());
			dto.setDateOfBirth(user.getDateOfBirth());
			dto.setGender(user.isGender());
			dto.setJoinDate(user.getJoinDate());
			dto.setPhoneNumber(user.getPhoneNumber());
			dto.setEmail(user.getEmail());
			dto.setAddress(user.getAddress());
			dto.setRole(user.getRole());
			dto.setStatus(user.isStatus());
//			dto.setCars(user.getCar());
//			dto.setContract_user(user.getContract_user());
//			dto.setContract_owner(user.getContract_owner());
//			dto.setFeedback(user.getFeedback());
//			dto.setPayments(user.getPayments());
//			dto.setRentCar(user.getRentCar());
//			dto.setDrivingLicense(user.getDrivingLicense());
			return dto;
		}
		return null;
		
	}
	
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
	
    public boolean changePassword(int id, String currentPassword, String newPassword) {
        User user = userRepository.findById(id);
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
	
	public User saveUser(int id, UserDTO dto) {
		User user = userRepository.findById(id);
		if(user != null) {
			user.setAvatar(dto.getAvatar());
			user.setFullName(dto.getFullName());
			user.setDateOfBirth(dto.getDateOfBirth());
			user.setGender(dto.isGender());
			user.setJoinDate(dto.getJoinDate());
			user.setPhoneNumber(dto.getPhoneNumber());
			user.setEmail(dto.getEmail());
			user.setAddress(dto.getAddress());
			user.setRole(dto.getRole());
			user.setStatus(dto.isStatus());
//			user.setCar(dto.getCar());
//			user.setContract_user(dto.getContract_user());
//			user.setContract_owner(dto.getContract_owner());
//			user.setFeedback(dto.getFeedback());
//			user.setPayments(dto.getPayments());
//			user.setRentCar(dto.getRentCar());
//			user.setDrivingLicense(dto.getDrivingLicense());
			userRepository.save(user);

			return user;
		}

		return null;
	}
	
	public UserDTO getByUserName(String username) {
		UserDTO dto;
		User user = userRepository.findByUsername(username);
		
		if (user != null) {
			dto = new UserDTO();
			/* Set all the values */
			dto.setId(user.getId());
			dto.setAvatar(user.getAvatar());
			dto.setFullName(user.getFullName());
			dto.setDateOfBirth(user.getDateOfBirth());
			dto.setGender(user.isGender());
			dto.setJoinDate(user.getJoinDate());
			dto.setPhoneNumber(user.getPhoneNumber());
			dto.setEmail(user.getEmail());
			dto.setAddress(user.getAddress());
			dto.setRole(user.getRole());
			dto.setStatus(user.isStatus());
//			dto.setCars(user.getCar());
//			dto.setContract_user(user.getContract_user());
//			dto.setContract_owner(user.getContract_owner());
//			dto.setFeedback(user.getFeedback());
//			dto.setPayments(user.getPayments());
//			dto.setRentCar(user.getRentCar());
//			dto.setDrivingLicense(user.getDrivingLicense());
			return dto;
		}
		return null;
	}
}
