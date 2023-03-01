package com.interviewSchedular.Login.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data

public class LoginRequest {
	@NotNull
	@NotBlank
	private String userName;
	
	@NotBlank
	@NotNull
	private String password;
	
	
}
