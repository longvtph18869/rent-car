package com.example.rent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rent.DTO.UserDTO;
import com.example.rent.entities.User;
import com.example.rent.repositories.UserRepository;

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
			dto.setAddress(user.getAddress());
			dto.setRole(user.getRole());
			dto.setStatus(user.isStatus());
			dto.setCars(user.getCar());
			dto.setContract_user(user.getContract_user());
			dto.setContract_owner(user.getContract_owner());
			dto.setFeedback(user.getFeedback());
			dto.setPayments(user.getPayments());
			dto.setRentCar(user.getRentCar());
			dto.setDrivingLicense(user.getDrivingLicense());
			return dto;
		}
		return null;
		
	}
//	public UserDTO saveOne(UserDTO dto) {
//		if(dto != null){
//			User user = null;
//			if(id > 0){
//				if(dto.getId() > 0 && !(dto.getId() == id))
//					return null;
//				user = userRepository.findById(id);
//			}
//			if(user == null)
//				user = new User();

			/* Set all the values */
//			user.setAvatar(dto.getAvatar());
//			user.setFullName(dto.getFullName());
//			user.setDateOfBirth(dto.getDateOfBirth());
//			user.setGender(dto.isGender());
//			user.setJoinDate(dto.getJoinDate());
//			user.setPhoneNumber(dto.getPhoneNumber());
//			user.setAddress(dto.getAddress());
//			user.setRole(dto.getRole());
//			user.setStatus(dto.isStatus());
//			user.setDrivingLicense(dto.getDrivingLicense());
//			if(user != null)
//				return new UserDTO(user);
//		}
//
//		return null;
//	}
	
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
			dto.setAddress(user.getAddress());
			dto.setRole(user.getRole());
			dto.setStatus(user.isStatus());
			dto.setCars(user.getCar());
			dto.setContract_user(user.getContract_user());
			dto.setContract_owner(user.getContract_owner());
			dto.setFeedback(user.getFeedback());
			dto.setPayments(user.getPayments());
			dto.setRentCar(user.getRentCar());
			dto.setDrivingLicense(user.getDrivingLicense());
			return dto;
		}
		return null;
	}
}
