package com.design.parkinglot.service;


import com.design.parkinglot.model.ParkingFloor;
import com.design.parkinglot.model.ParkingSpot;
import com.design.parkinglot.model.User;
import com.design.parkinglot.repository.ParkingFloorRepository;
import com.design.parkinglot.repository.ParkingSpotRepository;
import com.design.parkinglot.repository.UserRepository;
import com.design.parkinglot.repository_service.ParkingFloorService;
import com.design.parkinglot.repository_service.ParkingSpotService;
import com.design.parkinglot.repository_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ParkingService {

    @Autowired
    private UserService userService;

    @Autowired
    private ParkingFloorService parkingFloorService;

    @Autowired
    private ParkingSpotService parkingSpotService;

    public ResponseEntity<?> parkUser(User user){
        ParkingSpot parkingSpot = parkingSpotService.getParkingSpot(user.getParkingSpotId());
        ParkingFloor parkingFloor = parkingFloorService.getParkingFloor(parkingSpot.getParkingFloorId());
        if(!parkingSpot.getIsOccupied() && parkingSpot.getIsEnabled() && parkingFloor.getIsEnabled()){
            parkingSpot.setIsOccupied(Boolean.TRUE);
            user.setEntryTime(new Date());
            user.setIsEnabled(Boolean.TRUE);
            parkingFloor.setTotalSlotsAvailable(parkingFloor.getTotalSlotsAvailable() - 1);
            parkingFloor.setTotalSlotsBooked(parkingFloor.getTotalSlotsBooked() + 1);
            parkingSpotService.updateParkingSpot(parkingSpot);
            parkingFloorService.updateParkingFloor(parkingFloor);
            user = userService.createUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>("Parking Spot is Occupied", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> freeUser(Long id){
        User user = userService.getUser(id);
        ParkingSpot parkingSpot = parkingSpotService.getParkingSpot(user.getParkingSpotId());
        ParkingFloor parkingFloor = parkingFloorService.getParkingFloor(parkingSpot.getParkingFloorId());
        if(parkingSpot.getIsOccupied()){
            parkingSpot.setIsOccupied(Boolean.FALSE);
            user.setExitTime(new Date());
            user.setIsEnabled(Boolean.FALSE);
            user.setAmount(calculateParkingAmount(parkingSpot, user));
            parkingFloor.setTotalSlotsAvailable(parkingFloor.getTotalSlotsAvailable() + 1);
            parkingFloor.setTotalSlotsBooked(parkingFloor.getTotalSlotsBooked() - 1);
            parkingSpotService.updateParkingSpot(parkingSpot);
            parkingFloorService.updateParkingFloor(parkingFloor);
            user = userService.updateUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>("Parking Spot is already free", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> getAllParkingFloor(){
        List<ParkingFloor> parkingFloors = parkingFloorService.getAllParkingFloor();
        return new ResponseEntity<>(parkingFloors, HttpStatus.OK);
    }

    private Double calculateParkingAmount(ParkingSpot parkingSpot, User user) {
        Double amount;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(user.getExitTime().getTime() - user.getEntryTime().getTime());
        amount = minutes / 60d * parkingSpot.getHourlyRate();
        if(minutes > parkingSpot.getMaxMinParkPermitted()){
            amount += (minutes - parkingSpot.getMaxMinParkPermitted()) / 60d * parkingSpot.getHourlyRate() * 20/100;
        }
        return amount;
    }

}
