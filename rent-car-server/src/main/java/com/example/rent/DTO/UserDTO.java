package com.example.rent.DTO;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.OneToMany;

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
    private String username;
    private String password;
    private String role;
    private boolean status;
    private DrivingLicenseDTO drivingLicense;
    @OneToMany(mappedBy="user")
    private List<CarDTO> car;
    @OneToMany(mappedBy="user")
    private List<ContractDTO> contract_user;
    @OneToMany(mappedBy="owner")
    private List<ContractDTO> contract_owner;
    @OneToMany(mappedBy="user")
    private List<FeedbackDTO> feedback;
    @OneToMany(mappedBy="user")
    private List<PaymentDTO> payments;
    @OneToMany(mappedBy="user")
    private List<RentCarDTO> rentCar;
    
	public UserDTO() {
		super();
	}

	public UserDTO(int id, String avatar, String fullName, LocalDateTime dateOfBirth, boolean gender,
			LocalDateTime joinDate, String phoneNumber, String address, String username, String password, String role,
			boolean status, DrivingLicenseDTO drivingLicense, List<CarDTO> car, List<ContractDTO> contract_user,
			List<ContractDTO> contract_owner, List<FeedbackDTO> feedback, List<PaymentDTO> payments,
			List<RentCarDTO> rentCar) {
		super();
		this.id = id;
		this.avatar = avatar;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.joinDate = joinDate;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.username = username;
		this.password = password;
		this.role = role;
		this.status = status;
		this.drivingLicense = drivingLicense;
		this.car = car;
		this.contract_user = contract_user;
		this.contract_owner = contract_owner;
		this.feedback = feedback;
		this.payments = payments;
		this.rentCar = rentCar;
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public DrivingLicenseDTO getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(DrivingLicenseDTO drivingLicense) {
		this.drivingLicense = drivingLicense;
	}

	public List<CarDTO> getCar() {
		return car;
	}

	public void setCar(List<CarDTO> car) {
		this.car = car;
	}

	public List<ContractDTO> getContract_user() {
		return contract_user;
	}

	public void setContract_user(List<ContractDTO> contract_user) {
		this.contract_user = contract_user;
	}

	public List<ContractDTO> getContract_owner() {
		return contract_owner;
	}

	public void setContract_owner(List<ContractDTO> contract_owner) {
		this.contract_owner = contract_owner;
	}

	public List<FeedbackDTO> getFeedback() {
		return feedback;
	}

	public void setFeedback(List<FeedbackDTO> feedback) {
		this.feedback = feedback;
	}

	public List<PaymentDTO> getPayments() {
		return payments;
	}

	public void setPayments(List<PaymentDTO> payments) {
		this.payments = payments;
	}

	public List<RentCarDTO> getRentCar() {
		return rentCar;
	}

	public void setRentCar(List<RentCarDTO> rentCar) {
		this.rentCar = rentCar;
	}
}
