package com.example.rent.DTO;

import java.time.LocalDateTime;
import java.util.List;

import com.example.rent.entities.Car;
import com.example.rent.entities.Contract;
import com.example.rent.entities.DrivingLicense;
import com.example.rent.entities.Feedback;
import com.example.rent.entities.Payments;
import com.example.rent.entities.RentCar;
import com.example.rent.entities.User;

public class UserDTO {
    private int id;
    private String avatar;
    private String fullName;
    private LocalDateTime dateOfBirth;
    private boolean gender;
    private LocalDateTime joinDate;
    private String phoneNumber;
    private String address;
    private String role;
    private boolean status;
    private List<Car> cars;
    private List<Contract> contract_user;
    private List<Contract> contract_owner;
    private List<Feedback> feedback;
    private List<Payments> payments;
    private List<RentCar> rentCar;
    private DrivingLicense drivingLicense;
    
	public UserDTO() {
		super();
	}
	
	public UserDTO(int id, String avatar, String fullName, LocalDateTime dateOfBirth, boolean gender,
			LocalDateTime joinDate, String phoneNumber, String address, String role, boolean status, List<Car> cars,
			List<Contract> contract_user, List<Contract> contract_owner, List<Feedback> feedback,
			List<Payments> payments, List<RentCar> rentCar, DrivingLicense drivingLicense) {
		super();
		this.id = id;
		this.avatar = avatar;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.joinDate = joinDate;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.role = role;
		this.status = status;
		this.cars = cars;
		this.contract_user = contract_user;
		this.contract_owner = contract_owner;
		this.feedback = feedback;
		this.payments = payments;
		this.rentCar = rentCar;
		this.drivingLicense = drivingLicense;
	}

//	public UserDTO(User user) {
//		super();
//		if (user != null) {
//			this.id = user.getId();
//			this.avatar = user.getAvatar();
//			this.fullName = user.getFullName();
//			this.dateOfBirth = user.getDateOfBirth();
//			this.gender = user.isGender();
//			this.joinDate = user.getJoinDate();
//			this.phoneNumber = user.getPhoneNumber();
//			this.address = user.getAddress();
//			this.role = user.getRole();
//			this.status = user.isStatus();
//			
//			this.drivingLicense = user.getDrivingLicense();
//		}
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDateTime getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public LocalDateTime getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDateTime joinDate) {
		this.joinDate = joinDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public List<Contract> getContract_user() {
		return contract_user;
	}

	public void setContract_user(List<Contract> contract_user) {
		this.contract_user = contract_user;
	}

	public List<Contract> getContract_owner() {
		return contract_owner;
	}

	public void setContract_owner(List<Contract> contract_owner) {
		this.contract_owner = contract_owner;
	}

	public List<Feedback> getFeedback() {
		return feedback;
	}

	public void setFeedback(List<Feedback> feedback) {
		this.feedback = feedback;
	}

	public List<Payments> getPayments() {
		return payments;
	}

	public void setPayments(List<Payments> payments) {
		this.payments = payments;
	}

	public List<RentCar> getRentCar() {
		return rentCar;
	}

	public void setRentCar(List<RentCar> rentCar) {
		this.rentCar = rentCar;
	}

	public DrivingLicense getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(DrivingLicense drivingLicense) {
		this.drivingLicense = drivingLicense;
	}
}
