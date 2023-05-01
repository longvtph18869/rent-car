package com.example.rent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rent.DTO.CarDTO;
import com.example.rent.entities.Car;
import com.example.rent.service.CarService;

@RestController
@CrossOrigin
@RequestMapping("/find")
public class FindController {
	@Autowired
	CarService carService;
	
	@GetMapping("")
	public ResponseEntity<List<CarDTO>> getAllCar() {
	    try {
	        List<CarDTO> carList = carService.getAllCar();
	        return ResponseEntity.ok().body(carList);
	    } catch (Exception e) {
	    	System.out.println(e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}

}
