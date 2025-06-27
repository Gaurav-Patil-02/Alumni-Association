<<<<<<< HEAD
package com.example.alumniassociation.controller;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.alumniassociation.dto.UserDTO;
import com.example.alumniassociation.model.User;
import com.example.alumniassociation.model.UserRole;
import com.example.alumniassociation.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    
    @PutMapping("/promote/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> promoteToAdmin(@PathVariable String username) {
        Optional<User> optionalUser = userService.findByUsername(username);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user = optionalUser.get();

       
        if (UserRole.ADMIN.equals(user.getRole())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is already an admin");
        }

        user.setRole(UserRole.ADMIN);
        userService.save(user);

        // Log the promotion action
        logger.info("User with username {} has been promoted to Admin by an existing Admin.", username);

        return ResponseEntity.ok("User promoted to Admin");
    }
    
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsersWithUsernames();
    }

}
=======
package com.example.alumniassociation.controller;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.alumniassociation.dto.UserDTO;
import com.example.alumniassociation.model.User;
import com.example.alumniassociation.model.UserRole;
import com.example.alumniassociation.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    
    @PutMapping("/promote/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> promoteToAdmin(@PathVariable String username) {
        Optional<User> optionalUser = userService.findByUsername(username);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user = optionalUser.get();

       
        if (UserRole.ADMIN.equals(user.getRole())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is already an admin");
        }

        user.setRole(UserRole.ADMIN);
        userService.save(user);

        // Log the promotion action
        logger.info("User with username {} has been promoted to Admin by an existing Admin.", username);

        return ResponseEntity.ok("User promoted to Admin");
    }
    
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsersWithUsernames();
    }

}
>>>>>>> f41e2e3 (Add project files)
