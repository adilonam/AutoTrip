package com.example.tripapi.services;

import com.example.tripapi.models.Driver;
import com.example.tripapi.models.Mission;
import com.example.tripapi.models.Vehicle;

import java.util.List;
import java.util.Date;
public interface MissionService {
    List<Mission> getAllMissions();
    Mission createMission(Mission mission);
    List<Mission> createMissions(List<Mission> missions);

    List<Driver> availableDrivers(Date startDate, Date endDate) ;
    List<Vehicle> availableVehicles(Date startDate, Date endDate);
}
