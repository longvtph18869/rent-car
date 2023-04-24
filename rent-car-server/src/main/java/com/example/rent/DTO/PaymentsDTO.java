package com.example.rent.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentsDTO {
	private int id;
	private BigDecimal amount;
	private LocalDateTime paymentDate;
	private String paymentMethod;
	private RentCarDTO rentCar;
	private UserDTO user;

	public PaymentsDTO() {
		super();
	}

	public PaymentsDTO(int id, BigDecimal amount, LocalDateTime paymentDate, String paymentMethod, RentCarDTO rentCar,
			UserDTO user) {
		super();
		this.id = id;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.paymentMethod = paymentMethod;
		this.rentCar = rentCar;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public RentCarDTO getRentCar() {
		return rentCar;
	}

	public void setRentCar(RentCarDTO rentCar) {
		this.rentCar = rentCar;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
}
