package com.example.rent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rent.entities.Payments;

public interface PaymentRepository extends JpaRepository<Payments, Integer> {

}
