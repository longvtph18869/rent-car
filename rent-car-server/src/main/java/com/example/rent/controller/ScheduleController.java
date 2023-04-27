package com.example.rent.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rent.DTO.ScheduleDTO;
import com.example.rent.entities.RentalSchedule;
import com.example.rent.repositories.ScheduleRepository;
import com.example.rent.service.ScheduleService;

@RestController
@CrossOrigin
@RequestMapping("/schedule")
public class ScheduleController {
	@Autowired
	ScheduleService scheduleService;
	@Autowired
	ScheduleRepository scheduleRepository;

	@GetMapping("")
	public ResponseEntity<List<ScheduleDTO>> findByCar(@RequestParam("car") Integer carid) {
		try {
			List<ScheduleDTO> list = scheduleService.getByCar(carid);
			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/save")
	public ResponseEntity<List<RentalSchedule>> addSchedules(@RequestBody List<ScheduleDTO> scheduleDTOs) {
		List<RentalSchedule> rentalSchedules = new ArrayList<>();
		for (ScheduleDTO scheduleDTO : scheduleDTOs) {
			try {
				RentalSchedule rentalSchedule = scheduleService.addSchedule(scheduleDTO);
				rentalSchedules.add(rentalSchedule);
			} catch (Exception e) {
				System.out.println("Failed to add schedule: " + e.getMessage());
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		scheduleService.removeSchedule(scheduleDTOs);
		return ResponseEntity.ok().body(rentalSchedules);
	}
}
