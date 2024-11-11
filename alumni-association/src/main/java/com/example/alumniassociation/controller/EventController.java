package com.example.alumniassociation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

import com.example.alumniassociation.customException.EventNotFoundException;
import com.example.alumniassociation.customException.UserNotFoundException;
import com.example.alumniassociation.dto.EventDTO;
import com.example.alumniassociation.dto.RegisterDTO;
import com.example.alumniassociation.model.Event;
import com.example.alumniassociation.model.EventStatus;
import com.example.alumniassociation.model.Registration;
import com.example.alumniassociation.model.User;
import com.example.alumniassociation.service.EventService;
import com.example.alumniassociation.service.RegistrationService;
import com.example.alumniassociation.service.UserService;
import com.example.alumniassociation.util.JwtUtil;

import java.util.List;
import java.util.*;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RegistrationService registrationService;
   
   
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@RequestBody EventDTO eventDTO) {
        Event event = new Event();
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setDate(eventDTO.getDate());
        event.setLocation(eventDTO.getLocation());
        event.setOrganizer(eventDTO.getOrganizer());
        event.setStatus(EventStatus.PENDING);
       
        Event createdEvent = eventService.createEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }
    
  
    @PostMapping("/{eventId}/register")
    public ResponseEntity<Map<String, String>> registerForEvent(@PathVariable Long eventId, @RequestBody RegisterDTO registrationDto, @RequestHeader("Authorization") String tokenHeader) {
        Map<String, String> response = new HashMap<>();
        
        System.out.println("Event id :" + eventId);
        System.out.println("User id :" + registrationDto.getUserId());
        
         	
        try {
           
        	registrationService.registerUserForEvent(eventId, registrationDto.getUserId());
            response.put("status", "Registration successful");
            return ResponseEntity.ok(response);
        } catch (EventNotFoundException e) {
            System.out.println("Event not found exception: " + e.getMessage());
            response.put("error", "Event not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (UserNotFoundException e) {
            System.out.println("User not found exception: " + e.getMessage());
            response.put("error", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (RuntimeException e) {
            System.out.println("RuntimeException: " + e.getMessage());
            response.put("error", "Error registering for event");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @DeleteMapping("/{eventId}/unregister")
    public ResponseEntity<Map<String, String>> unregisterFromEvent(
            @PathVariable Long eventId, 
            @RequestBody RegisterDTO registrationDto, 
            @RequestHeader("Authorization") String tokenHeader) {
        
        Map<String, String> response = new HashMap<>();
       
        try {
            
        	registrationService.unregisterUserFromEvent(eventId, registrationDto.getUserId());
            response.put("status", "Unregistration successful");
            return ResponseEntity.ok(response);
        } catch (EventNotFoundException e) {
            System.out.println("Event not found exception: " + e.getMessage());
            response.put("error", "Event not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (UserNotFoundException e) {
            System.out.println("User not found exception: " + e.getMessage());
            response.put("error", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (RuntimeException e) {
            System.out.println("RuntimeException: " + e.getMessage());
            response.put("error", "Error unregistering from event");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }







    
    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Event> updateEventStatus(@PathVariable Long id, @RequestBody EventStatus status) {
        Event updatedEvent = eventService.updateEventStatus(id, status);
        return ResponseEntity.ok(updatedEvent);
    }


    
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        Event updatedEvent = eventService.updateEvent(id, eventDetails);
        updatedEvent.setStatus(EventStatus.PENDING); 
        return ResponseEntity.ok(updatedEvent);
    }

    
    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}/approve")
    public ResponseEntity<Event> approveEvent(@PathVariable Long id) {
        Event approvedEvent = eventService.updateEventStatus(id, EventStatus.APPROVED);
        return ResponseEntity.ok(approvedEvent);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}/reject")
    public ResponseEntity<Event> rejectEvent(@PathVariable Long id) {
        Event rejectedEvent = eventService.updateEventStatus(id, EventStatus.REJECTED);
        return ResponseEntity.ok(rejectedEvent);
    }

  
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    
    @GetMapping("/{id}/status")
    public ResponseEntity<EventStatus> getEventStatus(@PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isPresent()) {
            return ResponseEntity.ok(event.get().getStatus());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/{eventId}/registration-status")
    public ResponseEntity<Map<String, String>> getRegistrationStatus(@PathVariable Long eventId, Principal principal) {
      
        Map<String, String> response = new HashMap<>();
        
        if (principal == null) {
            
            response.put("error", "User is not authenticated");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        
        String username = principal.getName();
        Optional<User> userOptional = userService.findByUsername(username);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            
            boolean isRegistered = registrationService.isUserRegisteredForEvent(eventId, user.getId());
            
            if (isRegistered) {
                response.put("status", "User is registered for this event.");
            } else {
                response.put("status", "User is not registered for this event.");
            }
            
            return ResponseEntity.ok(response);  // Return JSON response
        } else {
          
            response.put("error", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }


}
}