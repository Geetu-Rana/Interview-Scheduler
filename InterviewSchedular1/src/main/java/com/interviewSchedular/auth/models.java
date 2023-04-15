package com.interviewSchedular.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
public class models {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	@NotNull
	private String name;
	
	@NotNull
	private String username;
	
	@NotNull
	@Column(unique = true)
	@Email(message = "email is not proper format")
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s])[A-Za-z\\d^\\s]{8,15}$\r\n", message = "Sorry, your password must be 8-15 characters long and contain at least one uppercase letter, one numeric character, and one special character.")
	private String password;
	
	private String address;
	
	@NotNull
	private String role;
}
