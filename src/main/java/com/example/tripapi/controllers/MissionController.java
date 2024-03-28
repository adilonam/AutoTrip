package com.example.tripapi.controllers;

import com.example.tripapi.models.Driver;
import com.example.tripapi.models.Mission;
import com.example.tripapi.models.Vehicle;
import com.example.tripapi.services.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/missions")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @GetMapping
    public ResponseEntity<List<Mission>> getAllMissions() {
        List<Mission> missions = missionService.getAllMissions();
        return ResponseEntity.ok(missions);
    }

  @GetMapping("/available-drivers")
public ResponseEntity<List<Driver>> getAvailableDrivers(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                        @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
    List<Driver> availableDrivers = missionService.availableDrivers(startDate, endDate);
    return ResponseEntity.ok(availableDrivers);
}


 @GetMapping("/available-vehicles")
    public ResponseEntity<List<Vehicle>> getAvailableVehicles(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                              @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        List<Vehicle> availableVehicles = missionService.availableVehicles(startDate, endDate);
        return ResponseEntity.ok(availableVehicles);
    }

    @PostMapping
    public ResponseEntity<Mission> createMission(@RequestBody Mission mission) {
        Mission newMission = missionService.createMission(mission);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMission);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Mission>> createMissionsBatch(@RequestBody List<Mission> missions) {
        List<Mission> createdMissions = missionService.createMissions(missions);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMissions);
    }
}
