package com.example.rent.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.rent.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer > {
	@Query("SELECT c FROM car c JOIN location l ON c.id = l.car WHERE l.latitude BETWEEN :minLat AND :maxLat AND l.longitude BETWEEN :minLong AND :maxLong")
	List<Car> filter(@Param("minLat") double minLat, @Param("maxLat") double maxLat, @Param("minLong") double minLong, @Param("maxLong") double maxLong);
}
