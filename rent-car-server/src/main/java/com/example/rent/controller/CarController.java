package com.example.rent.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@CrossOrigin()
@RequestMapping("/car")
public class CarController {
	@Autowired
	CarService carService;
	@GetMapping("/{id}")
	public ResponseEntity<CarDTO> findById(@PathVariable Integer id) {
	    try {
	        CarDTO carDTO= carService.findByID(id);
	        return ResponseEntity.ok().body(carDTO);
	    } catch (Exception e) {
	    	System.out.println(e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	
	@GetMapping("/filter")
	public ResponseEntity<List<CarDTO>> filter(
	    @RequestParam("latitude") String latitude,
	    @RequestParam("longitude") String longitude,
	    @RequestParam("pickupDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDate pickupDate,
	    @RequestParam("returnDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDate returnDate
	) {
	    try {
	        List<CarDTO> carList = carService.filter(latitude, longitude, pickupDate, returnDate);
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
	 public ResponseEntity<CarDTO> registerCar(@RequestBody CarDTO carDTO) {
		 try {
	        Car savedCar = carService.registerCar(carDTO);
	        return ResponseEntity.ok().body(carDTO);
		 } catch (Exception e) {
		    	System.out.println(e);
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		    }
	    }
	 @PutMapping(value = "/updateCar")
	 public ResponseEntity<Car> updateCar(@RequestBody CarDTO carDTO) {
		 try {
	        Car savedCar = carService.updateCar(carDTO);
	        return ResponseEntity.ok().body(savedCar);
		 } catch (Exception e) {
		    	System.out.println(e);
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		    }
	    }
	 @PutMapping("/{id}")
	    public ResponseEntity<Car> updateCarStatus(@PathVariable int id, @RequestParam("status") Integer status) {
	        try {
	            Car updatedCar = carService.updateCarStatus(id, status);
	            return ResponseEntity.ok(updatedCar);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
}
