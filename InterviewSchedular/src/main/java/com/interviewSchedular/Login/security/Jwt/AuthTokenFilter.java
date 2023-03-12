package com.interviewSchedular.Login.security.Jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.interviewSchedular.Login.security.Service.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthTokenFilter extends OncePerRequestFilter {
	
	private JwtUtils jwtUtils;
	
	private UserDetailsServiceImpl userDetailsService;
	
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		 try {
		      String jwt = parseJwt(request);
		      
		      if (jwt != null && jwtUtils.vlidateJwtToken(jwt)) {
		    	  
		        String username = jwtUtils.getUserNameFromJwtToken(jwt);

		        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		        
		        UsernamePasswordAuthenticationToken authentication = 
		            new UsernamePasswordAuthenticationToken(userDetails,
		                                                    null,
		                                                    userDetails.getAuthorities());
		        
		        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		        SecurityContextHolder.getContext().setAuthentication(authentication);
		      }
		    } catch (Exception e) {
		      logger.error("Cannot set user authentication: {}", e);
		    }

		    filterChain.doFilter(request, response);
		
	}
	private String parseJwt(HttpServletRequest request) {
	    String jwt = jwtUtils.getJwtFromCookies(request);
	    return jwt;
	  }
	
}
