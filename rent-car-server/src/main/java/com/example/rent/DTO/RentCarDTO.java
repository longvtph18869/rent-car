package com.example.rent.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.rent.entities.Car;
import com.example.rent.entities.Contract;
import com.example.rent.entities.Feedback;
import com.example.rent.entities.Payments;
import com.example.rent.entities.User;

public class RentCarDTO {
	private int id;
	private LocalDateTime rentalDate;
	private LocalDateTime returnDate;
	private LocalDateTime rentalDuration;
	private String pickupLocation;
	private String returnLocation;
	private BigDecimal rentalPrice;
	private boolean rentalStatus;
	private List<ContractDTO> contract;
	private List<FeedbackDTO> feedback;
	private PaymentsDTO payments;
	private UserDTO user;
	private CarDTO car;
	
	public RentCarDTO() {
		super();
	}

	public RentCarDTO(int id, LocalDateTime rentalDate, LocalDateTime returnDate, LocalDateTime rentalDuration,
			String pickupLocation, String returnLocation, BigDecimal rentalPrice, boolean rentalStatus,
			List<ContractDTO> contract, List<FeedbackDTO> feedback, PaymentsDTO payments, UserDTO user, CarDTO car) {
		super();
		this.id = id;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
		this.rentalDuration = rentalDuration;
		this.pickupLocation = pickupLocation;
		this.returnLocation = returnLocation;
		this.rentalPrice = rentalPrice;
		this.rentalStatus = rentalStatus;
		this.contract = contract;
		this.feedback = feedback;
		this.payments = payments;
		this.user = user;
		this.car = car;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(LocalDateTime rentalDate) {
		this.rentalDate = rentalDate;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	public LocalDateTime getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(LocalDateTime rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public String getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public String getReturnLocation() {
		return returnLocation;
	}

	public void setReturnLocation(String returnLocation) {
		this.returnLocation = returnLocation;
	}

	public BigDecimal getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(BigDecimal rentalPrice) {
		this.rentalPrice = rentalPrice;
	}

	public boolean isRentalStatus() {
		return rentalStatus;
	}

	public void setRentalStatus(boolean rentalStatus) {
		this.rentalStatus = rentalStatus;
	}

	public List<ContractDTO> getContract() {
		return contract;
	}

	public void setContract(List<ContractDTO> contract) {
		this.contract = contract;
	}

	public List<FeedbackDTO> getFeedback() {
		return feedback;
	}

	public void setFeedback(List<FeedbackDTO> feedback) {
		this.feedback = feedback;
	}

	public PaymentsDTO getPayments() {
		return payments;
	}

	public void setPayments(PaymentsDTO payments) {
		this.payments = payments;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public CarDTO getCar() {
		return car;
	}

	public void setCar(CarDTO car) {
		this.car = car;
	}
}
