package com.example.rent.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentCarDTO {
	private int id;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private LocalDateTime pickupDate;
    private String pickupLocation;
    private String returnLocation;
    private int rentalPrice;
    private int rentalStatus;
    private int paymentStatus;
    private int userId;
    private int ownerId;
    private CarDTO car;
}
