package com.example.rent.DTO;

public class ContractDTO {
    private int id;
    private boolean status;
    private UserDTO user;
    private UserDTO owner;
    private CarDTO car;
    private RentCarDTO rentCar;
    
    public ContractDTO() {
    	super();
    }
    
	public ContractDTO(int id, boolean status, UserDTO user, UserDTO owner, CarDTO car, RentCarDTO rentCar) {
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
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public UserDTO getOwner() {
		return owner;
	}
	public void setOwner(UserDTO owner) {
		this.owner = owner;
	}
	public CarDTO getCar() {
		return car;
	}
	public void setCar(CarDTO car) {
		this.car = car;
	}
	public RentCarDTO getRentCar() {
		return rentCar;
	}
	public void setRentCar(RentCarDTO rentCar) {
		this.rentCar = rentCar;
	}
}
