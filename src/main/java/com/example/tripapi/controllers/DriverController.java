package com.example.tripapi.controllers;

import com.example.tripapi.models.Driver;
import com.example.tripapi.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    // Endpoint to list all drivers
    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
            UserDetails userDetails = (UserDetails) token.getPrincipal();
            String username = userDetails.getUsername();
           System.out.println("********************************");
           System.out.println(username);
           System.out.println("********************************");
        }
        List<Driver> drivers = driverService.getAllDrivers();
        return ResponseEntity.ok(drivers);
    }

    // Endpoint to create a new driver
    @PostMapping
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {
        Driver newDriver = driverService.createDriver(driver);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDriver);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Driver>> createDrivers(@RequestBody List<Driver> drivers) {
        List<Driver> createdDrivers = driverService.createDrivers(drivers);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDrivers);
    }
}

