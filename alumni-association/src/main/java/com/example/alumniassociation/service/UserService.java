<<<<<<< HEAD
package com.example.alumniassociation.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.example.alumniassociation.customException.UserNotFoundException;
import com.example.alumniassociation.dto.UserDTO;
import com.example.alumniassociation.model.User;
import com.example.alumniassociation.repository.UserRepository;
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
   
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

 
    public void registerUser(User user) {
        
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }

        
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

       
        userRepository.save(user);

        System.out.println("User registered: " + user.getUsername() + ", with hashed password: " + hashedPassword);
    }


    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Attempting to load user by username: {}", username);
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            logger.error("User not found with username: {}", username);
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        User user = userOptional.get();
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));

        logger.info("User {} has roles: {}", user.getUsername(), authorities);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,  
                true,  
                authorities 
        );
    }



    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);
    }	
    
    public Long findUserIdByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(User::getId)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username, null));
    }
    public Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

         
            if (principal instanceof User) {
                return ((User) principal).getId();
            }
            
           
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                if (userDetails instanceof User) {
                    return ((User) userDetails).getId();
                }
            }

           
            if (principal instanceof String) {
                String username = (String) principal;
                return findUserIdByUsername(username);
                
            }
        }

        return null;
    }
    public List<UserDTO> getAllUsersWithUsernames() {
        List<User> users = userRepository.findAll(); 
        return users.stream()
                    .map(user -> new UserDTO(user.getUsername(), user.getFullName(), user.getRole())) 
                    .collect(Collectors.toList()); 
    }

	
	}
=======
package com.example.alumniassociation.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.example.alumniassociation.customException.UserNotFoundException;
import com.example.alumniassociation.dto.UserDTO;
import com.example.alumniassociation.model.User;
import com.example.alumniassociation.repository.UserRepository;
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
   
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

 
    public void registerUser(User user) {
        
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }

        
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

       
        userRepository.save(user);

        System.out.println("User registered: " + user.getUsername() + ", with hashed password: " + hashedPassword);
    }


    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Attempting to load user by username: {}", username);
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            logger.error("User not found with username: {}", username);
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        User user = userOptional.get();
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));

        logger.info("User {} has roles: {}", user.getUsername(), authorities);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,  
                true,  
                authorities 
        );
    }



    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);
    }	
    
    public Long findUserIdByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(User::getId)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username, null));
    }
    public Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

         
            if (principal instanceof User) {
                return ((User) principal).getId();
            }
            
           
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                if (userDetails instanceof User) {
                    return ((User) userDetails).getId();
                }
            }

           
            if (principal instanceof String) {
                String username = (String) principal;
                return findUserIdByUsername(username);
                
            }
        }

        return null;
    }
    public List<UserDTO> getAllUsersWithUsernames() {
        List<User> users = userRepository.findAll(); 
        return users.stream()
                    .map(user -> new UserDTO(user.getUsername(), user.getFullName(), user.getRole())) 
                    .collect(Collectors.toList()); 
    }

	
	}
>>>>>>> f41e2e3 (Add project files)
