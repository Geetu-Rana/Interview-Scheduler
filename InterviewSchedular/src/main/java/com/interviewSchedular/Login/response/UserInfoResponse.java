package com.interviewSchedular.Login.response;

import java.util.Set;

import lombok.Data;
@Data
public class UserInfoResponse {
	
	private Long id;
	
	private String userName;
	
	private String email;
	
	private Set<String> roles;
	
	public UserInfoResponse(Long id, String userName, String email, Set<String> roles) {
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.roles = roles;
	}
	
}
