package com.example.tripapi.repositories;

import com.example.tripapi.models.Vehicle;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

     @Query("SELECT v FROM Vehicle v WHERE v NOT IN "
            + "(SELECT m.vehicle FROM Mission m WHERE m.missionBegin BETWEEN :startDate AND :endDate "
            + "OR m.missionEnd BETWEEN :startDate AND :endDate)")
    List<Vehicle> findAvailableVehicles(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
