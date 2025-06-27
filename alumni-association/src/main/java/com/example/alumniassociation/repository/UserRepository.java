<<<<<<< HEAD
package com.example.alumniassociation.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.alumniassociation.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
=======
package com.example.alumniassociation.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.alumniassociation.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
>>>>>>> f41e2e3 (Add project files)
