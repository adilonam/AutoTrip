package com.example.tripapi.services;

import com.example.tripapi.models.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getAllVehicles();
    Vehicle createVehicle(Vehicle vehicle);
    List<Vehicle> createVehicles(List<Vehicle> vehicles);
}
