package com.design.parkinglot.repository_service;


import com.design.parkinglot.model.ParkingFloor;
import com.design.parkinglot.repository.ParkingFloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingFloorService {

    @Autowired
    private ParkingFloorRepository parkingFloorRepository;

    public ParkingFloor createParkingFloor(ParkingFloor parkingFloor){
        parkingFloor = parkingFloorRepository.save(parkingFloor);
        return parkingFloor;
    }

    public Boolean deleteParkingFloor(Long id) {
        parkingFloorRepository.deleteById(id);
        return true;
    }

    public ParkingFloor getParkingFloor(Long id) {
        ParkingFloor parkingFloor = parkingFloorRepository.getById(id);
        return parkingFloor;
    }

    public ParkingFloor updateParkingFloor(ParkingFloor parkingFloor) {
        parkingFloor = parkingFloorRepository.save(parkingFloor);
        return parkingFloor;
    }

    public List<ParkingFloor> getAllParkingFloor() {
        List<ParkingFloor> parkingFloors = parkingFloorRepository.findAll();
        return parkingFloors;
    }
}
