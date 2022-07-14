package com.design.parkinglot.repository_service;


import com.design.parkinglot.model.ParkingSpot;
import com.design.parkinglot.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ParkingSpotService {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public ParkingSpot createParkingSpot(ParkingSpot parkingSpot){
        parkingSpot = parkingSpotRepository.save(parkingSpot);
        return parkingSpot;
    }

    public Boolean deleteParkingSpot(Long id) {
        parkingSpotRepository.deleteById(id);
        return true;
    }

    public ParkingSpot getParkingSpot(Long id) {
        ParkingSpot parkingSpot = parkingSpotRepository.getById(id);
        return parkingSpot;
    }

    public ParkingSpot updateParkingSpot(ParkingSpot parkingSpot) {
        parkingSpot = parkingSpotRepository.save(parkingSpot);
        return parkingSpot;
    }
}
