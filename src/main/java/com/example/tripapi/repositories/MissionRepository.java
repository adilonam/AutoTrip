package com.example.tripapi.repositories;

import com.example.tripapi.models.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Integer> {

}
