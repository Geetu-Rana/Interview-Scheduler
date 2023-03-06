package com.interviewSchedular.Login.Controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.interviewSchedular.Login.repository.RoleRepository;
import com.interviewSchedular.Login.repository.UserRepository;
import com.interviewSchedular.Login.request.LoginRequest;
import com.interviewSchedular.Login.response.UserInfoResponse;
import com.interviewSchedular.Login.security.Jwt.JwtUtils;
import com.interviewSchedular.Login.security.Service.UserDetailsImpl;

import jakarta.validation.Valid;

@Controller
public class LoginController {

	private AuthenticationManager authenticationManager;

	private UserRepository userRepository;
	
	private RoleRepository repository;
	
	private PasswordEncoder encoder;
	
	private JwtUtils jwtUtils;
	
	@Autowired
	public LoginController(AuthenticationManager authenticationManager, UserRepository userRepository,
			RoleRepository repository, PasswordEncoder encoder, JwtUtils jwtUtils) {
		super();
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.repository = repository;
		this.encoder = encoder;
		this.jwtUtils = jwtUtils;
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
		List<String> roles = userDetails.getAuthorities().stream().map(obj-> obj.getAuthority()).toList();
		
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), new HashSet<>(roles)));
	}
	
	
	
}