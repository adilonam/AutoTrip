package com.example.tripapi.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tripapi.models.Driver;
import com.example.tripapi.repositories.DriverRepository;


@Service
public class DriverServiceImpl implements DriverService{
       @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }


        @Override
    public List<Driver> createDrivers(List<Driver> drivers) {
        List<Driver> createdDrivers = new ArrayList<>();
        for (Driver driver : drivers) {
            Driver createdDriver = createDriver(driver);
            createdDrivers.add(createdDriver);
        }
        return createdDrivers;
    }
}
