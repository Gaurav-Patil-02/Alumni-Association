<<<<<<< HEAD
package com.example.alumniassociation.dto;

import java.time.LocalDateTime;

public class EventDTO {

    private Long id; 

    private String title;
    private String description;
    private LocalDateTime date;
    private String location;
    private String organizer;
    private int capacity;
    // Constructors
    public EventDTO() {}

    public EventDTO(Long id, String title, String description, LocalDateTime date, String location, String organizer, int capacity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.organizer = organizer;
        this.capacity = capacity;
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

    @Override
    public String toString() {
        return "EventDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", createdBy='" + organizer + '\'' +
                ", capacity='" + capacity + '\'' +
                '}';
    }
}
=======
package com.example.alumniassociation.dto;

import java.time.LocalDateTime;

public class EventDTO {

    private Long id; 

    private String title;
    private String description;
    private LocalDateTime date;
    private String location;
    private String organizer;
    private int capacity;
    // Constructors
    public EventDTO() {}

    public EventDTO(Long id, String title, String description, LocalDateTime date, String location, String organizer, int capacity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.organizer = organizer;
        this.capacity = capacity;
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

    @Override
    public String toString() {
        return "EventDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", createdBy='" + organizer + '\'' +
                ", capacity='" + capacity + '\'' +
                '}';
    }
}
>>>>>>> f41e2e3 (Add project files)
