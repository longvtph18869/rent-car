package com.example.rent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.rent.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM user u WHERE u.username = :username")
	User findByUsername(@Param("username") String username);
}
