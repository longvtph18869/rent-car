package com.example.rent.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.rent.DTO.ScheduleDTO;
import com.example.rent.entities.Car;
import com.example.rent.entities.RentalSchedule;
import com.example.rent.repositories.CarRepository;
import com.example.rent.repositories.ScheduleRepository;

@Service
public class ScheduleService {

	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Autowired
	CarRepository carRepository;
	
	public List<ScheduleDTO> getByCar(Integer carId) {
		Optional<Car> car = carRepository.findById(carId);
		List<RentalSchedule> rentalSchedules = scheduleRepository.findByCar(car.get());
		List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
		for (RentalSchedule rentalSchedule : rentalSchedules) {
			ScheduleDTO scheduleDTO = new ScheduleDTO();
			scheduleDTO.setDate(rentalSchedule.getStartDate());
			scheduleDTO.setAvailable(rentalSchedule.isAvailable());
			scheduleDTO.setCarId(rentalSchedule.getCar().getId());
			scheduleDTOs.add(scheduleDTO);
		}
		return scheduleDTOs;
	}
	
	public RentalSchedule addSchedule(ScheduleDTO scheduleDTO) {
	    LocalDate date = scheduleDTO.getDate();
	    int carId = scheduleDTO.getCarId();
	    Optional<Car> car = carRepository.findById(carId);
	    RentalSchedule existingSchedule = scheduleRepository.findByDateAndCar(date, car.get());
	    if (existingSchedule == null) {
	    	RentalSchedule schedule = new RentalSchedule();
		    schedule.setStartDate(date);
		    schedule.setAvailable(scheduleDTO.isAvailable());
		    schedule.setCar(null);
		    return scheduleRepository.save(schedule);
		}
	    return existingSchedule;
	}
	
	public void removeSchedule(List<ScheduleDTO> scheduleDTOs) {
		 List<LocalDate> dates = new ArrayList<>();
		    int carId = 0 ;
		    for (ScheduleDTO scheduleDTO : scheduleDTOs) {
		        dates.add(scheduleDTO.getDate());
		        carId = scheduleDTO.getCarId();
		    }
		    Optional<Car> car = carRepository.findById(carId);
		    List<RentalSchedule> rentalSchedulesToDelete = scheduleRepository.findByCarIdAndDateNotIn(car.get(), dates);
		    scheduleRepository.deleteAll(rentalSchedulesToDelete);
	}
}
