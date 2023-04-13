package com.example.rent.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="user")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(columnDefinition = "varchar(255) CHARACTER SET utf8")
    private String fullName;
    @Column(columnDefinition = "varchar(255) CHARACTER SET utf8")
    private String phoneNumber;
    @Column(columnDefinition = "varchar(255) CHARACTER SET utf8")
    private String address;
    @Column(length=255)
    private String username;
    @Column(length=255)
    private String password;
    @Column(length=50)
    private String role;
    @Column(length=1)
    private boolean status;
    @OneToMany(mappedBy="user")
    private List<Car> car;
    @OneToMany(mappedBy="user")
    private List<Contract> contract_user;
    @OneToMany(mappedBy="owner")
    private List<Contract> contract_owner;
    @OneToMany(mappedBy="user")
    private List<Feedback> feedback;
    @OneToMany(mappedBy="user")
    private List<Payments> payments;
    @OneToMany(mappedBy="user")
    private List<RentCar> rentCar;
    @OneToOne
    @JoinColumn(name="driving_license_id")
    private DrivingLicense drivingLicense;

    public User() {
        super();
    }

	public User(int id, String fullName, String phoneNumber, String address, String username, String password,
			String role, boolean status) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.username = username;
		this.password = password;
		this.role = role;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public List<Car> getCar() {
		return car;
	}

	public void setCar(List<Car> car) {
		this.car = car;
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
