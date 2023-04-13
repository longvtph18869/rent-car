package com.example.rent.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rent.entities.Car;
import com.example.rent.service.CarService;

@RestController
@CrossOrigin
@RequestMapping("/car")
public class CarController {
	@Autowired
	CarService carService;
	@GetMapping("/{id}")
	public ResponseEntity<Car> findById(@PathVariable Integer id) {
	    try {
	        Optional<Car> optional= carService.findByID(id);
	        Car car = optional.get();
	        return ResponseEntity.ok().body(car);
	    } catch (Exception e) {
	    	System.out.println(e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
}
