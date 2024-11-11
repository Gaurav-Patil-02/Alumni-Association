package com.example.alumniassociation.controller;

import java.time.Year;
import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.alumniassociation.dto.LoginDTO;
import com.example.alumniassociation.model.User;
import com.example.alumniassociation.model.UserRole;
import com.example.alumniassociation.service.UserService;
import com.example.alumniassociation.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;




    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
          
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

           
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            
            
            String role = userDetails.getAuthorities().stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .findFirst()
                                    .orElse("ROLE_USER");  

           
            String token = jwtUtil.generateToken(userDetails.getUsername(), role);

            Map<String, String> response = new HashMap<>();
            response.put("token", "Bearer " + token);
            response.put("role", role);  

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Authentication failed");
        }
    }

   

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        
        // Log the incoming user object
        System.out.println("Received user: " + user);

        try {
        	int currentYear = Year.now().getValue();
        	if (user.getGraduationYear() != null && user.getGraduationYear() >= currentYear && user.getGraduationYear() <= currentYear + 4) {
        	    user.setRole(UserRole.STUDENT);
        	} else {
        	    user.setRole(UserRole.ALUMNI);
        	}

            userService.registerUser(user);
            
            response.put("message", "User registered successfully");
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

        } catch (Exception e) {
            System.err.println("Error during registration: " + e.getMessage());
            response.put("message", "Registration failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .body(response);
        }
    }


}
