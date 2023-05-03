package com.example.rent.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.rent.entities.Car;
import com.example.rent.entities.User;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer > {
	@Query("SELECT c FROM car c JOIN location l ON c.id = l.car"
			+ " JOIN rental_schedule rs ON c.id = rs.car AND (rs.startDate BETWEEN :pickupDate AND :returnDate AND rs.isAvailable = true)"
			+ "  AND NOT EXISTS (\r\n"
			+ "        SELECT rc FROM rent_car rc\r\n"
			+ "        WHERE rc.car = c\r\n"
			+ "        AND (\r\n"
			+ "            (rc.pickupDate < TIMESTAMP(:returnDate) AND rc.returnDate > TIMESTAMP(:pickupDate))"
			+ "        )\r\n"
			+ "    )"
			+ " WHERE l.latitude BETWEEN :minLat AND :maxLat AND l.longitude BETWEEN :minLong AND :maxLong GROUP BY c.id")
	List<Car> filter(@Param("minLat") double minLat, @Param("maxLat") double maxLat, @Param("minLong") double minLong, @Param("maxLong") double maxLong,@Param("pickupDate") LocalDate pickupDate,@Param("returnDate") LocalDate returnDate);
	@Query("SELECT c FROM car c WHERE c.user = :user")
	List<Car> findByUser (@Param("user") User user);
}
