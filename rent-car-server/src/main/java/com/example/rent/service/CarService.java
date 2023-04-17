package com.example.rent.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rent.entities.Car;
import com.example.rent.repositories.CarRepository;

@Service
public class CarService {
	@Autowired
	CarRepository carRepository;
	
	public List<Car> getAllCar() {
        return carRepository.findAll();
    }
	public Optional<Car> findByID(int id) {
        return carRepository.findById(id);
    }
	public List<Car> filter(String latitude, String longitude) {
        double delta = 0.1;

        double minLat = Double.parseDouble(latitude) - delta;
        double maxLat = Double.parseDouble(latitude)  + delta;
        double minLong = Double.parseDouble(longitude)  - delta;
        double maxLong = Double.parseDouble(longitude)  + delta;

        return carRepository.filter(minLat, maxLat, minLong, maxLong);
    }
}
