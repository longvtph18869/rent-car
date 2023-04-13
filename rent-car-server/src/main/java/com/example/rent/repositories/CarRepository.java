package com.example.rent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rent.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer > {

}
