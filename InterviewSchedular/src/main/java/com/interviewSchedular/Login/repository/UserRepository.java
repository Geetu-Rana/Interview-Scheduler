package com.interviewSchedular.Login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interviewSchedular.Login.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUserName(String username);

  	Boolean existsByUserName(String username);

  	Boolean existsByEmail(String email);
}
