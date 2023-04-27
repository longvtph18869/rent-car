package com.example.rent.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.rent.entities.Car;
import com.example.rent.entities.RentalSchedule;

public interface ScheduleRepository extends JpaRepository<RentalSchedule, Integer> {
	@Query("SELECT rs FROM rental_schedule rs WHERE rs.car = :car")
	List<RentalSchedule> findByCar(@Param("car") Car car);

	@Query("SELECT rs FROM rental_schedule rs WHERE rs.startDate = :date AND rs.car = :car")
	RentalSchedule findByDateAndCar(@Param("date") LocalDate date,@Param("car") Car car);
	
	@Query("SELECT rs FROM rental_schedule rs WHERE rs.car = :car AND rs.startDate NOT IN :dates")
	List<RentalSchedule> findByCarIdAndDateNotIn(@Param("car") Car car, @Param("dates") List<LocalDate> dates);
}
