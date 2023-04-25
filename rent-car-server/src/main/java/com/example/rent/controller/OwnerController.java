package com.example.rent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rent.DTO.CarDTO;
import com.example.rent.service.CarService;


@RestController
@CrossOrigin
@RequestMapping("/owner")
public class OwnerController {
	@Autowired
	CarService carService;
	@GetMapping("/mycars")
	public ResponseEntity<List<CarDTO>> getMyCar(@RequestParam("user") Integer userId){
		  try {
			  List<CarDTO> list = carService.findByUser(userId);
		        return ResponseEntity.ok().body(list);
		    } catch (Exception e) {
		    	System.out.println(e);
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		    }
	}
}
