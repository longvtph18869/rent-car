package com.example.rent.DTO;

import java.time.LocalDateTime;

public class DrivingLicenseDTO {
	private int id;
	private String number;
	private String fullName;
	private LocalDateTime birthDay;
	private String address;
	private LocalDateTime dateOfIssue;
	private LocalDateTime expirationDate;
	private String licenseClass;
	private String issuingAuthority;
	private boolean status;
	private UserDTO user;
	
	public DrivingLicenseDTO() {
		super();
	}

	public DrivingLicenseDTO(int id, String number, String fullName, LocalDateTime birthDay, String address,
			LocalDateTime dateOfIssue, LocalDateTime expirationDate, String licenseClass, String issuingAuthority,
			boolean status, UserDTO user) {
		super();
		this.id = id;
		this.number = number;
		this.fullName = fullName;
		this.birthDay = birthDay;
		this.address = address;
		this.dateOfIssue = dateOfIssue;
		this.expirationDate = expirationDate;
		this.licenseClass = licenseClass;
		this.issuingAuthority = issuingAuthority;
		this.status = status;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDateTime getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDateTime birthDay) {
		this.birthDay = birthDay;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDateTime getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(LocalDateTime dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getLicenseClass() {
		return licenseClass;
	}

	public void setLicenseClass(String licenseClass) {
		this.licenseClass = licenseClass;
	}

	public String getIssuingAuthority() {
		return issuingAuthority;
	}

	public void setIssuingAuthority(String issuingAuthority) {
		this.issuingAuthority = issuingAuthority;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
}
