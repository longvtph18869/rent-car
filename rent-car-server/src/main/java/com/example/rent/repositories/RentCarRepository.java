package com.example.rent.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.rent.entities.Car;
import com.example.rent.entities.RentCar;
import com.example.rent.entities.User;
@Repository
public interface RentCarRepository extends JpaRepository<RentCar, Integer>{
	 List<RentCar> findByUserOrderByRentalDateDesc(User user);
	 
	 List<RentCar> findByOwnerOrderByRentalDateDesc(User owner);
	 @Modifying
	 @Query("UPDATE rent_car r SET r.rentalStatus = :rentalStatus WHERE r.id = :rentalId")
	 void updateRentalStatus(@Param("rentalId") int rentalId, @Param("rentalStatus") int rentalStatus);
	 @Query("SELECT r FROM rent_car r WHERE r.car = :car")
	 List<RentCar> findByCar(@Param("car") Car car);
}
