package com.example.rent.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
	private int orderCode;
	private int amount;
	private String paymentUrl;
	private String bankCode;
	private int userId;
}
