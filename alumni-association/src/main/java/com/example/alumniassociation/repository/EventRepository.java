<<<<<<< HEAD
package com.example.alumniassociation.repository;

import com.example.alumniassociation.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByStatus(EventStatus status);
}
=======
package com.example.alumniassociation.repository;

import com.example.alumniassociation.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByStatus(EventStatus status);
}
>>>>>>> f41e2e3 (Add project files)
