 package com.example.rent.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.swing.text.html.HTMLDocument.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rent.DTO.PaymentDTO;
import com.example.rent.DTO.PaymentResponseDTO;
import com.example.rent.entities.Payments;
import com.example.rent.service.PaymentService;
import com.example.rent.service.VnpayService;


@RestController
@CrossOrigin
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	VnpayService vnpayService;
	
	@Autowired
	PaymentService paymentService;
	
	 @PostMapping("")
	    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {
	        try {
	            String paymentUrl = vnpayService.createPaymentRequest(paymentDTO);
	            paymentDTO.setPaymentUrl(paymentUrl);;
	            return ResponseEntity.ok(paymentDTO);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }
	 @PostMapping("/save")
	    public ResponseEntity<PaymentDTO> savePayment(@RequestBody PaymentDTO paymentDTO) {
	        try {
	            Payments payments = paymentService.createPayment(paymentDTO);
	            return ResponseEntity.ok(paymentDTO);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }
}
