package com.example.rent.DTO;

import java.time.LocalDateTime;

public class FeedbackDTO {
	private int id;
	private int rating;
	private String comment;
	private LocalDateTime feedbackDate;
	private UserDTO user;
	private RentCarDTO rentCar;

	public FeedbackDTO() {
		super();
	}

	public FeedbackDTO(int id, int rating, String comment, LocalDateTime feedbackDate, UserDTO user,
			RentCarDTO rentCar) {
		super();
		this.id = id;
		this.rating = rating;
		this.comment = comment;
		this.feedbackDate = feedbackDate;
		this.user = user;
		this.rentCar = rentCar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(LocalDateTime feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public RentCarDTO getRentCar() {
		return rentCar;
	}

	public void setRentCar(RentCarDTO rentCar) {
		this.rentCar = rentCar;
	}
}
