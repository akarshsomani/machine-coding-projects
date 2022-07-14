package com.design.parkinglot.repository;

import com.design.parkinglot.model.ParkingSpot;
import com.design.parkinglot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

}
