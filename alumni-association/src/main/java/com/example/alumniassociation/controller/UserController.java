<<<<<<< HEAD
package com.example.alumniassociation.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.alumniassociation.dto.ProfileDTO;
import com.example.alumniassociation.dto.UpdateProfileDTO;
import com.example.alumniassociation.model.User;
import com.example.alumniassociation.service.UserService;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

  
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileDTO> getProfile(Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));



        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setFullName(user.getFullName());
        profileDTO.setGraduationYear(user.getGraduationYear());
        profileDTO.setFieldOfStudy(user.getFieldOfStudy());
        profileDTO.setCurrentOccupation(user.getCurrentOccupation());
        
        System.out.println("name :"+profileDTO.getFullName());
        System.out.println("year :"+profileDTO.getGraduationYear());
        System.out.println("study :"+profileDTO.getFieldOfStudy());
        System.out.println("occ :"+profileDTO.getCurrentOccupation());

        return ResponseEntity.ok(profileDTO);
    }

    @PutMapping("/profile/update")
    public ResponseEntity<UpdateProfileDTO> updateProfile(@RequestBody UpdateProfileDTO updateProfileDTO, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));


      
        user.setFullName(updateProfileDTO.getFullName());
        user.setGraduationYear(updateProfileDTO.getGraduationYear());
        user.setFieldOfStudy(updateProfileDTO.getFieldOfStudy());
        user.setCurrentOccupation(updateProfileDTO.getCurrentOccupation());

        userService.save(user);  

        return ResponseEntity.ok(updateProfileDTO);
    }
}
=======
package com.example.alumniassociation.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.alumniassociation.dto.ProfileDTO;
import com.example.alumniassociation.dto.UpdateProfileDTO;
import com.example.alumniassociation.model.User;
import com.example.alumniassociation.service.UserService;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

  
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileDTO> getProfile(Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));



        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setFullName(user.getFullName());
        profileDTO.setGraduationYear(user.getGraduationYear());
        profileDTO.setFieldOfStudy(user.getFieldOfStudy());
        profileDTO.setCurrentOccupation(user.getCurrentOccupation());
        
        System.out.println("name :"+profileDTO.getFullName());
        System.out.println("year :"+profileDTO.getGraduationYear());
        System.out.println("study :"+profileDTO.getFieldOfStudy());
        System.out.println("occ :"+profileDTO.getCurrentOccupation());

        return ResponseEntity.ok(profileDTO);
    }

    @PutMapping("/profile/update")
    public ResponseEntity<UpdateProfileDTO> updateProfile(@RequestBody UpdateProfileDTO updateProfileDTO, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));


      
        user.setFullName(updateProfileDTO.getFullName());
        user.setGraduationYear(updateProfileDTO.getGraduationYear());
        user.setFieldOfStudy(updateProfileDTO.getFieldOfStudy());
        user.setCurrentOccupation(updateProfileDTO.getCurrentOccupation());

        userService.save(user);  

        return ResponseEntity.ok(updateProfileDTO);
    }
}
>>>>>>> f41e2e3 (Add project files)
