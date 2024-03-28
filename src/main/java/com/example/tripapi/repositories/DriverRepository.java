package com.example.tripapi.repositories;

import com.example.tripapi.models.Driver;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Query("SELECT d FROM Driver d WHERE d NOT IN "
            + "(SELECT m.driver FROM Mission m WHERE m.missionBegin BETWEEN :startDate AND :endDate "
            + "OR m.missionEnd BETWEEN :startDate AND :endDate)")
    List<Driver> findAvailableDrivers(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}

