package com.example.rent.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

	private Long payment_id;
	private int amount;
	private String description;
	private String bankCode;
}
