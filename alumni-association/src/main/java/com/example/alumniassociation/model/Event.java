<<<<<<< HEAD
package com.example.alumniassociation.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDateTime date;
    private String location;
    private String organizer;
    private int capacity;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @ManyToMany
    @JoinTable(
        name = "event_registrations",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> registrations = new ArrayList<>();
    
    
    public Event() {
        this.status = EventStatus.PENDING; 
    }

    public Event(String title, String description, LocalDateTime date, String location, String organizer, int capacity, EventStatus status) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.organizer = organizer;
        this.capacity = capacity;
        this.status = status;
    }

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public List<User> getRegistrations() {
        return registrations;
    }

    public void addParticipant(User user) {
        if (registrations.size() < capacity) {
            registrations.add(user);
        } else {
            throw new RuntimeException("Event is full");
        }
    }
    
    public void removeParticipant(User user) {
    	registrations.remove(user); 
    }
    public boolean isUserRegistered(User user) {
        return registrations.contains(user);
    }

	

	
    
}
=======
package com.example.alumniassociation.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDateTime date;
    private String location;
    private String organizer;
    private int capacity;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @ManyToMany
    @JoinTable(
        name = "event_registrations",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> registrations = new ArrayList<>();
    
    
    public Event() {
        this.status = EventStatus.PENDING; 
    }

    public Event(String title, String description, LocalDateTime date, String location, String organizer, int capacity, EventStatus status) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.organizer = organizer;
        this.capacity = capacity;
        this.status = status;
    }

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public List<User> getRegistrations() {
        return registrations;
    }

    public void addParticipant(User user) {
        if (registrations.size() < capacity) {
            registrations.add(user);
        } else {
            throw new RuntimeException("Event is full");
        }
    }
    
    public void removeParticipant(User user) {
    	registrations.remove(user); 
    }
    public boolean isUserRegistered(User user) {
        return registrations.contains(user);
    }

	

	
    
}
>>>>>>> f41e2e3 (Add project files)
