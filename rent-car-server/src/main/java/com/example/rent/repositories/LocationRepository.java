package com.example.rent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rent.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

}
