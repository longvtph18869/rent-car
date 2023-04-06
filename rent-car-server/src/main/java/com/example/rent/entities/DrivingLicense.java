package com.example.rent.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name="driving_license")
public class DrivingLicense {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(length=255)
    private String number;
    @Column(length=50)
    private String fullName;
    private LocalDateTime birthDay;
    @Column(length=255)
    private String address;
    @Column(name="date_of_issue")
    private LocalDateTime dateOfIssue;
    @Column(name="expiration_date")
    private LocalDateTime expirationDate;
    @Column(name="license_class", length=50)
    private String licenseClass;
    @Column(name="issuing_authority", length=50)
    private String issuingAuthority;
    @Column(length=1)
    private boolean status;
    @OneToOne(mappedBy="drivingLicense")
    private User user;

    public DrivingLicense() {
        super();
    }

	

	public DrivingLicense(int id, String number, String fullName, LocalDateTime birthDay, String address,
			LocalDateTime dateOfIssue, LocalDateTime expirationDate, String licenseClass, String issuingAuthority,
			boolean status, User user) {
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



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}

	

}
