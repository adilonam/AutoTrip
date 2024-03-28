package com.example.tripapi.services;

import com.example.tripapi.models.Driver;
import com.example.tripapi.models.Mission;
import com.example.tripapi.models.Vehicle;
import com.example.tripapi.repositories.DriverRepository;
import com.example.tripapi.repositories.MissionRepository;
import com.example.tripapi.repositories.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Date;

@Service
public class MissionServiceImpl implements MissionService {

    @Autowired
    private MissionRepository missionRepository;

      @Autowired
    private DriverRepository driverRepository;

      @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    @Override
    public Mission createMission(Mission mission) {
        return missionRepository.save(mission);
    }

    @Override
    public List<Mission> createMissions(List<Mission> missions) {
        return missionRepository.saveAll(missions);
    }


    @Override
    public List<Driver> availableDrivers(Date startDate, Date endDate) {
        // Retrieve all drivers who do not have a mission between the specified date range
        return driverRepository.findAvailableDrivers(startDate, endDate);
    }


     @Override
    public List<Vehicle> availableVehicles(Date startDate, Date endDate) {
        return vehicleRepository.findAvailableVehicles(startDate, endDate);
    }
}
