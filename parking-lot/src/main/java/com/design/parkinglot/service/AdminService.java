package com.design.parkinglot.service;


import com.design.parkinglot.model.ParkingFloor;
import com.design.parkinglot.model.ParkingSpot;
import com.design.parkinglot.model.User;
import com.design.parkinglot.repository_service.ParkingFloorService;
import com.design.parkinglot.repository_service.ParkingSpotService;
import com.design.parkinglot.repository_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class AdminService {


    @Autowired
    private ParkingFloorService parkingFloorService;

    @Autowired
    private ParkingSpotService parkingSpotService;

    public ResponseEntity<?> addFloor(){
        ParkingFloor parkingFloor = new ParkingFloor();
        parkingFloor.setParkingFloorName(generateRandom());
        parkingFloor.setIsEnabled(Boolean.TRUE);
        parkingFloor.setTotalSlots(0);
        parkingFloor.setTotalSlotsBooked(0);
        parkingFloor.setTotalSlotsAvailable(0);
        parkingFloor = parkingFloorService.createParkingFloor(parkingFloor);
        return new ResponseEntity<>(parkingFloor, HttpStatus.OK);
    }

    public ResponseEntity<?> addSpot(ParkingSpot parkingSpot){
        ParkingFloor parkingFloor = parkingFloorService.getParkingFloor(parkingSpot.getParkingFloorId());
        if(parkingFloor == null){
            return new ResponseEntity<>("Invalid Parking Floor", HttpStatus.BAD_REQUEST);
        }
        parkingSpot.setIsOccupied(Boolean.FALSE);
        parkingSpot.setIsEnabled(Boolean.TRUE);
        parkingFloor.setTotalSlots(parkingFloor.getTotalSlots() + 1);
        parkingFloor.setTotalSlotsAvailable(parkingFloor.getTotalSlotsAvailable() + 1);
        parkingFloorService.updateParkingFloor(parkingFloor);
        parkingSpot = parkingSpotService.createParkingSpot(parkingSpot);
        return new ResponseEntity<>(parkingSpot, HttpStatus.OK);
    }

    private String generateRandom() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }


}
