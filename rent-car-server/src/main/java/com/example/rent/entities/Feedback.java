package com.example.rent.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="feedback")
public class Feedback{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(precision=10)
    private int rating;
    @Column(length=255)
    private String comment;
    @Column(name="feedback_date")
    private LocalDateTime feedbackDate;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="rent_car_id")
    private RentCar rentCar;

    public Feedback() {
        super();
    }

	public Feedback(int id, int rating, String comment, LocalDateTime feedbackDate, User user, RentCar rentCar) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RentCar getRentCar() {
		return rentCar;
	}

	public void setRentCar(RentCar rentCar) {
		this.rentCar = rentCar;
	}  
}
