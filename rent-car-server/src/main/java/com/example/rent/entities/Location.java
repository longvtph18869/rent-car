package com.example.rent.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="location")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Location {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(length=255,columnDefinition = "varchar(255) CHARACTER SET utf8")
    private String name;
    @Column(length=255)
    private Double latitude;
    @Column(length=255)
    private Double longitude;
    @Column(length=1)
    private boolean status;
	@OneToOne
	@JoinColumn(name = "car_id")
	@JsonBackReference
	private Car car;

}
