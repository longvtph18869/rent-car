package com.example.rent.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="contract")
public class Contract {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(length=1)
    private boolean status;
    @ManyToOne
    @JoinColumn(name="customer_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="car_owner_id")
    private User owner;
    @ManyToOne
    @JoinColumn(name="car_id")
    private Car car;
    @ManyToOne
    @JoinColumn(name="rent_car_id")
    private RentCar rentCar;

    public Contract() {
        super();
    }

	public Contract(int id, boolean status, User user, User owner, Car car, RentCar rentCar) {
		super();
		this.id = id;
		this.status = status;
		this.user = user;
		this.owner = owner;
		this.car = car;
		this.rentCar = rentCar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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



	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public RentCar getRentCar() {
		return rentCar;
	}

	public void setRentCar(RentCar rentCar) {
		this.rentCar = rentCar;
	}

    
}
