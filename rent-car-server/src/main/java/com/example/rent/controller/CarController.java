package com.example.rent.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rent.DTO.CarDTO;
import com.example.rent.entities.Car;
import com.example.rent.entities.Manufacturer;
import com.example.rent.enums.CarColor;
import com.example.rent.enums.CarType;
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
	
	@GetMapping("/filter")
	public ResponseEntity<List<Car>> filter(@RequestParam String latitude, @RequestParam String longitude) {
	    try {
	    	List<Car> carList = carService.filter(latitude,longitude);
	        return ResponseEntity.ok().body(carList);
	    } catch (Exception e) {
	    	System.out.println(e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	 @GetMapping("/enums")
	 public ResponseEntity<?> enums() {
		    try {
		    	Map<String, Object> enums = new HashMap<>();
		        enums.put("colors", CarColor.values());
		        List<Integer> values = new ArrayList<>();
		        for (CarType type : CarType.values()) {
		            values.add(type.getValue());
		        }
		        enums.put("seats", values);
		        return ResponseEntity.ok().body(enums);
		    } catch (Exception e) {
		    	System.out.println(e);
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		    }
		}
	 @GetMapping("/manufacturers")
	 public ResponseEntity<List<Manufacturer>> getAllManufacturers() {
		    try {
		    	List<Manufacturer> manufacturers = carService.getAllManufacturers();
		        return ResponseEntity.ok().body(manufacturers);
		    } catch (Exception e) {
		    	System.out.println(e);
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		    }
		}
	 @PostMapping(value = "/registerCar")
	 public ResponseEntity<Car> registerCar(@RequestBody CarDTO carDTO) {
		 try {
	        Car savedCar = carService.registerCar(carDTO);
	        return ResponseEntity.ok().body(savedCar);
		 } catch (Exception e) {
		    	System.out.println(e);
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		    }
	    }
}
