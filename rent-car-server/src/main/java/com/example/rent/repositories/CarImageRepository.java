package com.example.rent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rent.entities.Car;
import com.example.rent.entities.CarImage;

public interface CarImageRepository extends JpaRepository<CarImage, Integer> {
	void deleteByCar(Car car);
}
