package com.interviewSchedular.Login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interviewSchedular.Login.model.ERole;
import com.interviewSchedular.Login.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role> findByName(ERole name);
	
}
