package com.example.tripapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    private Date missionBegin;

    private Date missionEnd;

    @ManyToOne
    private Driver driver;


    @ManyToOne
    private Vehicle vehicle;

  

}
