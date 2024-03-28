package com.example.tripapi.services;

import com.example.tripapi.models.Driver;

import java.util.List;

public interface DriverService {

    // Method to retrieve all drivers
    List<Driver> getAllDrivers();

    // Method to create a new driver
    Driver createDriver(Driver driver);
    List<Driver> createDrivers( List<Driver> drivers);

}