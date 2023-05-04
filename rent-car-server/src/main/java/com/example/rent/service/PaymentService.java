package com.example.rent.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rent.DTO.PaymentDTO;
import com.example.rent.entities.Payments;
import com.example.rent.entities.RentCar;
import com.example.rent.entities.User;
import com.example.rent.repositories.PaymentRepository;
import com.example.rent.repositories.RentCarRepository;
import com.example.rent.repositories.UserRepository;
@Service
public class PaymentService {
	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
     RentCarRepository rentCarRepository;

    @Autowired
    private UserRepository userRepository;
	public Payments createPayment(PaymentDTO paymentDTO) {
        Payments payment = new Payments();
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentMethod(paymentDTO.getBankCode());
        RentCar rentCar = rentCarRepository.findById(paymentDTO.getOrderCode())
            .orElseThrow(() -> new IllegalArgumentException("Invalid rent car id"));
        if (rentCar != null) {
        	rentCar.setPaymentStatus(1);
            rentCarRepository.save(rentCar);
		}
        payment.setRentCar(rentCar);
        User user = userRepository.findById(paymentDTO.getUserId());
        payment.setUser(user);
        return paymentRepository.save(payment);
    }
}
