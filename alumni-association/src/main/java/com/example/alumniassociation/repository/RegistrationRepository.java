package com.example.alumniassociation.repository;

import com.example.alumniassociation.model.Event;
import com.example.alumniassociation.model.Registration;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    List<Registration> findByEventId(Long eventId);

    List<Registration> findByUserId(Long userId);
    Optional<Registration> findByEventAndUserId(Event event, Long userId);
}
