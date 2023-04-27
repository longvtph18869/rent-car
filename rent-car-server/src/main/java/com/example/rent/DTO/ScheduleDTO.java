package com.example.rent.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ScheduleDTO {
	private LocalDate date;
	private boolean isAvailable;
	private int carId;
}
