package com.example.rent.DTO;

import java.math.BigDecimal;
import java.time.Year;
import com.example.rent.entities.Location;
import com.example.rent.entities.Manufacturer;
import com.example.rent.entities.User;
import com.example.rent.enums.CarColor;
import com.example.rent.enums.CarType;

public class CarDTO {
	private int id;
	private String name;
	private Year yearOfManufacture;
	private CarColor color;
	private CarType type;
	private BigDecimal rentalPrice;
	private String description;
	private boolean status;
	private User user;
	private Manufacturer manufacturer;
	private Location location;
	public CarDTO() {
		super();
	}
	public CarDTO(int id, String name, Year yearOfManufacture, CarColor color, CarType type, BigDecimal rentalPrice,
			String description, boolean status, User user, Manufacturer manufacturer, Location location) {
		super();
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Year getYearOfManufacture() {
		return yearOfManufacture;
	}
	public void setYearOfManufacture(Year yearOfManufacture) {
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
	
	
}
