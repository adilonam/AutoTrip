package com.example.tripapi.repositories.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tripapi.models.auth.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    

    Optional<User> findByEmail(String email) ;
}
