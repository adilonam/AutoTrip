package com.example.tripapi.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String registrationNumber;
    private String brand;
    private String model;
    private String type;

    private double mileage;

    private boolean availability;

    private String requiredLicenseType;
    private String specialEquipment;
    private String registrationCertificate;
    private Date technicalInspectionDate;
    private Date vignetteExpirationDate;



}
