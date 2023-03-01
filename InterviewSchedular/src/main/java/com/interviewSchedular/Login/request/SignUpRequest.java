package com.interviewSchedular.Login.request;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class SignUpRequest {
	
	private String userName;
	
	private String email;
	
	private Set<String> roles;
	
	@NotBlank
	@Size(min = 6, max = 14)
	private String password;
	
	
}
