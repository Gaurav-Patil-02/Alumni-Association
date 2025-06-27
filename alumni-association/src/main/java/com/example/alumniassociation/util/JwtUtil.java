<<<<<<< HEAD
package com.example.alumniassociation.util;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.alumniassociation.model.Event;
import com.example.alumniassociation.model.User;
import com.example.alumniassociation.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	@Value("${secret.key}")
	private String SECRET_KEY;

	
	 private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
	 
	 @Autowired
	 private UserRepository userRepository;

	 public String generateToken(String username, String role) {
		    logger.info("Generating token for username: {}", username);

		    Optional<User> userOptional = userRepository.findByUsername(username);
		    if (!userOptional.isPresent()) {
		        throw new UsernameNotFoundException("User not found with username: " + username);
		    }
		    User user = userOptional.get();
		    logger.info("User ID: {}", user.getId());

		    
		    Claims claims = Jwts.claims().setSubject(username);
		    claims.put("role", role);
		    claims.put("userId", user.getId());

		    String token = Jwts.builder()
		            .setClaims(claims)  
		            .setIssuedAt(new Date(System.currentTimeMillis()))
		            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  
		            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
		            .compact();
		    
		    logger.info("Token generated successfully: {}", token);
		    
		    Claims decodedClaims = Jwts.parser()
		            .setSigningKey(SECRET_KEY)
		            .parseClaimsJws(token)
		            .getBody();
		    logger.info("Decoded token claims: {}", decodedClaims);
		    return token;
		}
	 
	 
	 public Long extractUserIdFromToken(String token) {
		    Claims claims = Jwts.parser()
		        .setSigningKey(SECRET_KEY)
		        .parseClaimsJws(token)
		        .getBody();
		    
		    Long userId = claims.get("userId", Long.class); 
		    logger.info("Extracted userId from token: {}", userId);
		    return userId;
		}


	public String extractUsername(String token) {
		 logger.debug("Extracting username from token: {}", token);
		String username =  extractAllClaims(token).getSubject();
		 logger.debug("Extracting username from token: {}", token);
		return username;
	}

	private Claims extractAllClaims(String token) {
		 logger.debug("Extracting all claims from token...");
		Claims claims =  Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		logger.debug("Claims extracted: {}", claims);
		return claims;
	}

	public boolean isTokenExpired(String token) {
		logger.debug("Checking if token is expired...");
		boolean isExpired =  extractAllClaims(token).getExpiration().before(new Date());
		logger.info("Token expiration status: {}", isExpired ? "Expired" : "Valid");
		return isExpired;
	}

	public boolean validateToken(String token, String username) {
		 logger.info("Validating token for username: {}", username);
		final String extractedUsername = extractUsername(token);
		boolean isValid =  (extractedUsername.equals(username) && !isTokenExpired(token));
		 logger.info("Token validation result: {}", isValid ? "Valid" : "Invalid");
		 return isValid;
	}
}
=======
package com.example.alumniassociation.util;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.alumniassociation.model.Event;
import com.example.alumniassociation.model.User;
import com.example.alumniassociation.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	@Value("${secret.key}")
	private String SECRET_KEY;

	
	 private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
	 
	 @Autowired
	 private UserRepository userRepository;

	 public String generateToken(String username, String role) {
		    logger.info("Generating token for username: {}", username);

		    Optional<User> userOptional = userRepository.findByUsername(username);
		    if (!userOptional.isPresent()) {
		        throw new UsernameNotFoundException("User not found with username: " + username);
		    }
		    User user = userOptional.get();
		    logger.info("User ID: {}", user.getId());

		    
		    Claims claims = Jwts.claims().setSubject(username);
		    claims.put("role", role);
		    claims.put("userId", user.getId());

		    String token = Jwts.builder()
		            .setClaims(claims)  
		            .setIssuedAt(new Date(System.currentTimeMillis()))
		            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  
		            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
		            .compact();
		    
		    logger.info("Token generated successfully: {}", token);
		    
		    Claims decodedClaims = Jwts.parser()
		            .setSigningKey(SECRET_KEY)
		            .parseClaimsJws(token)
		            .getBody();
		    logger.info("Decoded token claims: {}", decodedClaims);
		    return token;
		}
	 
	 
	 public Long extractUserIdFromToken(String token) {
		    Claims claims = Jwts.parser()
		        .setSigningKey(SECRET_KEY)
		        .parseClaimsJws(token)
		        .getBody();
		    
		    Long userId = claims.get("userId", Long.class); 
		    logger.info("Extracted userId from token: {}", userId);
		    return userId;
		}


	public String extractUsername(String token) {
		 logger.debug("Extracting username from token: {}", token);
		String username =  extractAllClaims(token).getSubject();
		 logger.debug("Extracting username from token: {}", token);
		return username;
	}

	private Claims extractAllClaims(String token) {
		 logger.debug("Extracting all claims from token...");
		Claims claims =  Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		logger.debug("Claims extracted: {}", claims);
		return claims;
	}

	public boolean isTokenExpired(String token) {
		logger.debug("Checking if token is expired...");
		boolean isExpired =  extractAllClaims(token).getExpiration().before(new Date());
		logger.info("Token expiration status: {}", isExpired ? "Expired" : "Valid");
		return isExpired;
	}

	public boolean validateToken(String token, String username) {
		 logger.info("Validating token for username: {}", username);
		final String extractedUsername = extractUsername(token);
		boolean isValid =  (extractedUsername.equals(username) && !isTokenExpired(token));
		 logger.info("Token validation result: {}", isValid ? "Valid" : "Invalid");
		 return isValid;
	}
}
>>>>>>> f41e2e3 (Add project files)
