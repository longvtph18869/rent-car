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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "car")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
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
	@Enumerated(EnumType.ORDINAL)
	private CarType type;
	@Column(name = "rental_price", precision = 10)
	private BigDecimal rentalPrice;
	@Column(length = 65535,columnDefinition = "varchar(65535) CHARACTER SET utf8")
	private String description;
	@Column(length = 1)
	private boolean status;
	@ManyToOne
	@JoinColumn(name = "owner_id")
	@JsonManagedReference
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

}
