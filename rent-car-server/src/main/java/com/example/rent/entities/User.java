package com.example.rent.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(length=255)
    private String avatar;
    @Column(columnDefinition = "varchar(255) CHARACTER SET utf8")
    private String fullName;
    @Column(name="date_of_birth")
    private LocalDateTime dateOfBirth;
    @Column(name="gender", columnDefinition = "tinyint(1) default 1")
    private boolean gender;
    @Column(name="join_date")
    private LocalDateTime joinDate;
    @Column(columnDefinition = "varchar(255) CHARACTER SET utf8")
    private String phoneNumber;
    @Column(columnDefinition = "varchar(255) CHARACTER SET utf8")
    private String email;
    @Column(columnDefinition = "varchar(255) CHARACTER SET utf8")
    private String address;
    @Column(length=255)
    private String username;
    @Column(length=255)
    private String password;
    @Column(length=50)
    private String role;
    @Column(length=1)
    private boolean status;
    @OneToOne
	@JoinColumn(name = "drivingLicense_id")
    private DrivingLicense drivingLicense;
    @OneToMany(mappedBy="user")
    @JsonBackReference
    private List<Car> car;
    @OneToMany(mappedBy="user")
    private List<Contract> contract_user;
    @OneToMany(mappedBy="owner")
    private List<Contract> contract_owner;
    @OneToMany(mappedBy="owner")
    private List<RentCar> lease;
    @OneToMany(mappedBy="user")
    private List<Feedback> feedback;
    @OneToMany(mappedBy="user")
    private List<Payments> payments;
    @OneToMany(mappedBy="user")
    private List<RentCar> rentCar;
}
