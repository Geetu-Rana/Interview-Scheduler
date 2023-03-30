package com.interviewSchedular.Login.Controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interviewSchedular.Login.model.ERole;
import com.interviewSchedular.Login.model.Role;
import com.interviewSchedular.Login.model.User;
import com.interviewSchedular.Login.repository.UserRepository;
import com.interviewSchedular.Login.request.LoginRequest;
import com.interviewSchedular.Login.request.SignUpRequest;
import com.interviewSchedular.Login.response.MessageResponse;
import com.interviewSchedular.Login.response.UserInfoResponse;
import com.interviewSchedular.Login.security.Jwt.JwtUtils;
import com.interviewSchedular.Login.security.Service.UserDetailsImpl;

import jakarta.validation.Valid;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
		
		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
		
		List<String> roles = userDetails.getAuthorities().stream().map(obj-> obj.getAuthority()).toList();
		
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), new HashSet<>(roles)));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
		
		if(userRepository.existsByUserName(signUpRequest.getUserName())) {
			return ResponseEntity.badRequest().body(new MessageResponse(" Error: UserName Already taken"));
		}
		if(userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error email Allready Exist"));
		}
		
		
		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();
		User user = new User();
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(signUpRequest.getPassword());
		user.setUserName(signUpRequest.getUserName());
		if(strRoles == null) {
			Role userRole = new Role();
			userRole.setName(ERole.USER);
			roles.add(userRole);
			
		}else {
			strRoles.forEach(role ->{
					switch(role) {
					case "admin":
						Role adminRole = new Role();
						adminRole.setName(ERole.ADMIN);
						roles.add(adminRole);
						break;
					case "mod":
						Role employerRole = new Role();
						employerRole.setName(ERole.EMPLOYER);
						roles.add(employerRole);
						break;
					default:
						Role userRole = new Role();
						userRole.setName(ERole.USER);
						roles.add(userRole);
						break;
					}
					
			});
		}
		user.setRole(roles);
		userRepository.save(user);
		
		return ResponseEntity.ok(new MessageResponse("User Registered Successfully"));
	}
	
	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser(){
		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(new MessageResponse("Logged Out SuccessFully"));
	}
	
	@GetMapping("/hello")
	public ResponseEntity<String> getData(){ 
		return new ResponseEntity<String>("Hello",HttpStatus.ACCEPTED);
	}
	
}
