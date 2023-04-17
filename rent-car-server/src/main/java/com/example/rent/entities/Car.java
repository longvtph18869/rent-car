package com.example.rent.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.rent.enums.CarColor;
import com.example.rent.enums.CarType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "car")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(name = "license_plates", length = 50)
	private String licensePlates;
	@Column(name = "name", columnDefinition = "varchar(255) CHARACTER SET utf8")
	private String name;
	@Column(name = "year_of_manufacture", precision = 10)
	private int yearOfManufacture;
	@Column(length = 255)
	@Enumerated(EnumType.STRING)
	private CarColor color;
	@Column(length = 255)
	@Enumerated(EnumType.STRING)
	private CarType type;
	@Column(name = "rental_price", precision = 10)
	private BigDecimal rentalPrice;
	@Column(length = 255)
	private String description;
	@Column(length = 1)
	private boolean status;
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User user;
	@ManyToOne
	@JoinColumn(name = "manufacturer_id")
	@JsonManagedReference
	private Manufacturer manufacturer;
	@OneToOne(mappedBy = "car")
	@JsonManagedReference
	private Location location;
	@OneToMany(mappedBy = "car")
	@JsonManagedReference
	private List<CarImage> carImage;
	@OneToMany(mappedBy = "car")
	private List<Contract> contract;
	@OneToMany(mappedBy = "car")
	private List<RentalSchedule> rentalSchedule;
	@OneToMany(mappedBy = "car")
	private List<RentCar> rentCar;

	public Car() {
		super();
	}

	public Car(int id, String licensePlates, String name, int yearOfManufacture, CarColor color, CarType type,
			BigDecimal rentalPrice, String description, boolean status, User user, Manufacturer manufacturer,
			Location location) {
		super();
		this.id = id;
		this.licensePlates = licensePlates;
		this.name = name;
		this.yearOfManufacture = yearOfManufacture;
		this.color = color;
		this.type = type;
		this.rentalPrice = rentalPrice;
		this.description = description;
		this.status = status;
		this.user = user;
		this.manufacturer = manufacturer;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLicensePlates() {
		return licensePlates;
	}

	public void setLicensePlates(String licensePlates) {
		this.licensePlates = licensePlates;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYearOfManufacture() {
		return yearOfManufacture;
	}

	public void setYearOfManufacture(int yearOfManufacture) {
		this.yearOfManufacture = yearOfManufacture;
	}

	public CarColor getColor() {
		return color;
	}

	public void setColor(CarColor color) {
		this.color = color;
	}

	public CarType getType() {
		return type;
	}

	public void setType(CarType type) {
		this.type = type;
	}

	public BigDecimal getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(BigDecimal rentalPrice) {
		this.rentalPrice = rentalPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<CarImage> getCarImage() {
		return carImage;
	}

	public void setCarImage(List<CarImage> carImage) {
		this.carImage = carImage;
	}

	public List<Contract> getContract() {
		return contract;
	}

	public void setContract(List<Contract> contract) {
		this.contract = contract;
	}

	public List<RentalSchedule> getRentalSchedule() {
		return rentalSchedule;
	}

	public void setRentalSchedule(List<RentalSchedule> rentalSchedule) {
		this.rentalSchedule = rentalSchedule;
	}

	public List<RentCar> getRentCar() {
		return rentCar;
	}

	public void setRentCar(List<RentCar> rentCar) {
		this.rentCar = rentCar;
	}

}
