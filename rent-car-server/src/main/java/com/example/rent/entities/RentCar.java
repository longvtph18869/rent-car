package com.example.rent.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="rent_car")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentCar  {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(name="rental_date")
    private LocalDateTime rentalDate;
    @Column(name="return_date")
    private LocalDateTime returnDate;
    @Column(name="pickup_date")
    private LocalDateTime pickupDate;
    @Column(name="pickup_location", length=255)
    private String pickupLocation;
    @Column(name="return_location", length=255)
    private String returnLocation;
    @Column(name="rental_price")
    private int rentalPrice;
    @Column(name="rental_status")
    private int rentalStatus;
    @Column(name="payment_status")
    private int paymentStatus;
    @OneToMany(mappedBy="rentCar")
    private List<Contract> contract;
    @OneToMany(mappedBy="rentCar")
    private List<Feedback> feedback;
    @OneToOne(mappedBy="rentCar")
    private Payments payments;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner;
    @ManyToOne
    @JoinColumn(name="car_id")
    private Car car;

}
