<<<<<<< HEAD
package com.example.alumniassociation.service;



import com.example.alumniassociation.model.Event;
import com.example.alumniassociation.model.EventStatus;
import com.example.alumniassociation.repository.EventRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

  
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

   
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
        
    }

  
    public Event createEvent(Event event) {
        event.setStatus(EventStatus.PENDING);
        return eventRepository.save(event);
    }


    public Event updateEvent(Long id, Event eventDetails) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setTitle(eventDetails.getTitle());
        event.setDescription(eventDetails.getDescription());
        event.setDate(eventDetails.getDate());
        event.setLocation(eventDetails.getLocation());
        event.setOrganizer(eventDetails.getOrganizer());
        event.setCapacity(eventDetails.getCapacity());
        event.setStatus(eventDetails.getStatus());

        return eventRepository.save(event);
    }

   
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }


    public List<Event> getEventsByStatus(EventStatus status) {
        return eventRepository.findByStatus(status);
    }

    public Event updateEventStatus(Long id, EventStatus status) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setStatus(status);
        return eventRepository.save(event);
    }
    
   


}
=======
package com.example.alumniassociation.service;



import com.example.alumniassociation.model.Event;
import com.example.alumniassociation.model.EventStatus;
import com.example.alumniassociation.repository.EventRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

  
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

   
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
        
    }

  
    public Event createEvent(Event event) {
        event.setStatus(EventStatus.PENDING);
        return eventRepository.save(event);
    }


    public Event updateEvent(Long id, Event eventDetails) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setTitle(eventDetails.getTitle());
        event.setDescription(eventDetails.getDescription());
        event.setDate(eventDetails.getDate());
        event.setLocation(eventDetails.getLocation());
        event.setOrganizer(eventDetails.getOrganizer());
        event.setCapacity(eventDetails.getCapacity());
        event.setStatus(eventDetails.getStatus());

        return eventRepository.save(event);
    }

   
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }


    public List<Event> getEventsByStatus(EventStatus status) {
        return eventRepository.findByStatus(status);
    }

    public Event updateEventStatus(Long id, EventStatus status) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setStatus(status);
        return eventRepository.save(event);
    }
    
   


}
>>>>>>> f41e2e3 (Add project files)
