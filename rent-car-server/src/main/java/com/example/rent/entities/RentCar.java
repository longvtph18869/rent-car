package com.example.rent.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
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

@Entity(name="rent_car")
public class RentCar  {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(name="rental_date")
    private LocalDate rentalDate;
    @Column(name="return_date")
    private LocalDate returnDate;
    @Column(name="pickup_date")
    private LocalDate pickupDate;
    @Column(name="pickup_location", length=255)
    private String pickupLocation;
    @Column(name="return_location", length=255)
    private String returnLocation;
    @Column(name="rental_price", precision=10)
    private BigDecimal rentalPrice;
    @Column(name="rental_status", length=1)
    private boolean rentalStatus;
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
    @JoinColumn(name="car_id")
    private Car car;

    public RentCar() {
        super();
    }

}
