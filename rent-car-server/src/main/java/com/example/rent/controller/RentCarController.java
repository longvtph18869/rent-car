package com.example.rent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.rent.DTO.RentCarDTO;
import com.example.rent.entities.RentCar;
import com.example.rent.service.RentCarService;

@RestController
@CrossOrigin
@RequestMapping("/rent")
public class RentCarController {
	@Autowired
    private RentCarService rentCarService;
	@PostMapping
	public ResponseEntity<RentCarDTO> addRentCar(@RequestBody RentCarDTO rentCarDTO) {
	    try {
	    	RentCar rentCar = rentCarService.addRentCar(rentCarDTO);
	    	rentCarDTO.setId(rentCar.getId());
	    	return ResponseEntity.ok().body(rentCarDTO);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	
	@GetMapping("/rented")
	public ResponseEntity<List<RentCarDTO>> getRentalsForUser(@RequestParam("userId") int userId) {
	    try {
	    	List<RentCarDTO> rentalDTOs = rentCarService.findRentalsByUser(userId);
		    return ResponseEntity.ok(rentalDTOs);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}

	@GetMapping("/lease")
	public ResponseEntity<List<RentCarDTO>> getRentalsForOwner(@RequestParam("userId") int userId) {
	    try {
	    	List<RentCarDTO> rentalDTOs = rentCarService.findRentalsByOwner(userId);
		    return ResponseEntity.ok(rentalDTOs);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	@PutMapping("/{rentalId}")
    public ResponseEntity<Void> updateRentalStatus(@PathVariable int rentalId, @RequestParam("rentalStatus") int rentalStatus) {
		rentCarService.updateRentalStatus(rentalId, rentalStatus);
        return ResponseEntity.noContent().build();
    }
	@GetMapping("/car/{carId}")
	public ResponseEntity<List<RentCarDTO>> getRentalsByCar(@PathVariable int carId) {
	    try {
	    	List<RentCarDTO> rentalDTOs = rentCarService.findByCar(carId);
		    return ResponseEntity.ok(rentalDTOs);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
}
