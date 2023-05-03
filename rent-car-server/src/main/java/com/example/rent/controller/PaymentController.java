package com.example.rent.controller;

import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rent.DTO.PaymentDTO;
import com.example.rent.service.VnpayService;


@RestController
@CrossOrigin
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	VnpayService vnpayService;
	
	@PostMapping("")
    public ResponseEntity<String> pay(@RequestBody PaymentDTO paymentDTO) throws UnsupportedEncodingException {
        String paymentUrl = vnpayService.createPaymentRequest(paymentDTO);
        return ResponseEntity.ok(paymentUrl);
    }
}
