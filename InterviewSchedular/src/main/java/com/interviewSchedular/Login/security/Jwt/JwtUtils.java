package com.interviewSchedular.Login.security.Jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.web.util.WebUtils;
import com.interviewSchedular.Login.security.Service.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class JwtUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${interviewerSchedular.app.jwtSecret}")
	private String jwtSecret;
	
	@Value("${interviewerSchedular.app.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	@Value("${interviewerSchedular.app.jwtCookieName}")
	private String jwtCookie;
	
	public String getJwtFromCookies(HttpServletRequest request) {
		Cookie cookie = WebUtils.getCookie(request, jwtCookie);
		if(cookie != null) {
			return cookie.getValue();
		}else {
			return null;
		}
	}
	
	
	public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
		String jwt = generateTokenFromUserName(jwtCookie);
		ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24*60*60).httpOnly(true).build();
		return cookie;
	}
	
	public ResponseCookie getCleanJwtCookie() {
		
		ResponseCookie cookie = ResponseCookie.from(jwtCookie,null).path("/api").build();
		return cookie;
	}
	
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
	public boolean vlidateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		}catch(SignatureException e) {
			logger.error("Invalid JWT signatur: {}", e.getMessage());
		}catch (MalformedJwtException e) {
			// TODO: handle exception
			logger.error("Invalid JWT token : {}", e.getMessage());
		}catch(ExpiredJwtException e) {
			logger.error("JWT token is expired: {} ", e.getMessage());
		}catch(UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {} ", e.getMessage());
		}catch(IllegalArgumentException e) {
			logger.error("JWT claims start is empty: {}", e.getMessage());
		}
		return false;
			
	}
	
	public String generateTokenFromUserName(String userName) {
		return Jwts.builder()
				.setSubject(userName)
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	
}
