package com.design.parkinglot.controller;

import com.design.parkinglot.model.User;
import com.design.parkinglot.repository_service.UserService;
import com.design.parkinglot.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @PostMapping("/park")
    public ResponseEntity<?> parkUser(@RequestBody User user){
        return parkingService.parkUser(user);
    }

    @GetMapping("/release")
    public ResponseEntity<?> releaseUser(@RequestParam Long id){
        return parkingService.freeUser(id);
    }

    @GetMapping("/getAvailability")
    public ResponseEntity<?> getAllFloorsData(){
        return parkingService.getAllParkingFloor();
    }




//    @GetMapping("/get")
//    public ResponseEntity<?> getUser(@RequestParam Long id){
//        return userService.getUser(id);
//    }
//
//    @DeleteMapping("/delete")
//    public ResponseEntity<?> deleteUser(@RequestParam Long id){
//        return userService.deleteUser(id);
//    }


}
