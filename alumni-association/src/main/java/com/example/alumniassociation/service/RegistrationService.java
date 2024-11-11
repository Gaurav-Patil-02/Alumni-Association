package com.example.alumniassociation.service;

import com.example.alumniassociation.model.Event;
import com.example.alumniassociation.model.Registration;
import com.example.alumniassociation.model.User;
import com.example.alumniassociation.repository.EventRepository;
import com.example.alumniassociation.repository.RegistrationRepository;
import com.example.alumniassociation.repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    
    public Registration registerUserForEvent(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new RuntimeException("Event not found"));

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        Registration registration = new Registration();
        registration.setEvent(event);
        registration.setUser(user);

        return registrationRepository.save(registration);
    }
    
    public Event unregisterUserFromEvent(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

       
        Registration registration = registrationRepository.findByEventAndUserId(event, userId)
                .orElseThrow(() -> new RuntimeException("User is not registered for this event"));

        
        registrationRepository.delete(registration);

    
        event.getRegistrations().remove(registration);
        return eventRepository.save(event);
    }
    
    public boolean isUserRegisteredForEvent(Long eventId, Long userId) {
        System.out.println("Checking registration for eventId: " + eventId + " and userId: " + userId);
        
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        
        Optional<Registration> registration = registrationRepository.findByEventAndUserId(event, userId);
        System.out.println("Registration found: " + registration.isPresent());
        
        return registration.isPresent();
    }
}
