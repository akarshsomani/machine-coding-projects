package com.design.parkinglot.controller;

import com.design.parkinglot.model.ParkingSpot;
import com.design.parkinglot.model.User;
import com.design.parkinglot.service.AdminService;
import com.design.parkinglot.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/add-floor")
    public ResponseEntity<?> addFloor(){
        return adminService.addFloor();
    }

    @PostMapping("/add-spot")
    public ResponseEntity<?> addSpot(@RequestBody ParkingSpot parkingSpot){
        return adminService.addSpot(parkingSpot);
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
