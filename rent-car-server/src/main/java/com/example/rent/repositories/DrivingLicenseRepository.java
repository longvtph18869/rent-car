package com.example.rent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rent.entities.DrivingLicense;

public interface DrivingLicenseRepository extends JpaRepository<DrivingLicense, Long> {

}
