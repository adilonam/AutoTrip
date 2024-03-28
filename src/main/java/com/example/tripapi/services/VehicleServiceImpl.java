package com.example.tripapi.services;

import com.example.tripapi.models.Vehicle;
import com.example.tripapi.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public List<Vehicle> createVehicles(List<Vehicle> vehicles) {
        List<Vehicle> createdVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            Vehicle createdVehicle = createVehicle(vehicle);
            createdVehicles.add(createdVehicle);
        }
        return createdVehicles;
    }
}
