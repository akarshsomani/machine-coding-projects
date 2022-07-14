package com.design.parkinglot.repository;

import com.design.parkinglot.model.ParkingFloor;
import com.design.parkinglot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingFloorRepository extends JpaRepository<ParkingFloor, Long> {

}
