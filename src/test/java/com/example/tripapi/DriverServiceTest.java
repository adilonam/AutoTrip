package com.example.tripapi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.example.tripapi.models.Driver;
import com.example.tripapi.services.DriverService;
import java.util.List;

@SpringBootTest
@Transactional
public class DriverServiceTest {

    @Autowired
    private DriverService driverService;

    @Test
    public void testCreateDriver() {
        // Create a new driver
        Driver newDriver = Driver.builder()
                                .firstName("John")
                                .lastName("Doe")
                                .registrationNumber("123ABC")
                                .build();

        // Save the driver
        Driver createdDriver = driverService.createDriver(newDriver);

        // Verify that the created driver is not null
        assertNotNull(createdDriver.getId(), "Driver ID should not be null after creation");
    }

    @Test
    public void testGetAllDrivers() {
        // Create a new driver
        Driver newDriver = Driver.builder()
                                .firstName("John")
                                .lastName("Doe")
                                .registrationNumber("123ABC")
                                .build();

        // Save the driver
        driverService.createDriver(newDriver);

        // Retrieve all drivers
        List<Driver> allDrivers = driverService.getAllDrivers();

        // Verify that the list of drivers is not empty
        assertFalse(allDrivers.isEmpty(), "List of drivers should not be empty after creating a driver");
    }
}

