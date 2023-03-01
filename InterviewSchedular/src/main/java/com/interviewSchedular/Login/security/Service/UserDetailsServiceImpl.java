package com.interviewSchedular.Login.security.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.interviewSchedular.Login.model.User;
import com.interviewSchedular.Login.repository.UserRepository;

import jakarta.transaction.Transactional;

public class UserDetailsServiceImpl implements UserDetailsService {	
	  private  UserRepository userRepository;
		 
	  @Autowired
	  public UserDetailsServiceImpl(UserRepository userRepository ) {
		// TODO Auto-generated constructor stub
		  this.userRepository = userRepository;
	 }
	  
	  @Override
	  @Transactional
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    User user = userRepository.findByUsername(username)
	        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

	    return UserDetailsImpl.build(user);
	  }
	  
}
