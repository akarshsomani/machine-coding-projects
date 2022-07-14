package com.design.healthplatform.service;

import com.design.healthplatform.model.Device;
import com.design.healthplatform.model.User;
import com.design.healthplatform.repository.DeviceRepository;
import com.design.healthplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> registerDevice(Device device){
        User user = userRepository.getById(device.getUserId());
        if(user.getIsEnabled()){
            device.setIsEnabled(Boolean.TRUE);
            device.setRegisteredDate(new Date());
            device = deviceRepository.save(device);
            return new ResponseEntity<>(device, HttpStatus.OK);

        }
        return new ResponseEntity<>("User is not enabled", HttpStatus.BAD_REQUEST);
    }

//    public ResponseEntity<?> deleteDevice(Long id) {
//        deviceRepository.deleteById(id);
//        return new ResponseEntity<>(true, HttpStatus.OK);
//    }
//
//    public ResponseEntity<?> getDevice(Long id) {
//        Device device = deviceRepository.getById(id);
//        return new ResponseEntity<>(device, HttpStatus.OK);
//    }

    public ResponseEntity<?> getAllRegisteredDevice(Long userId){
        User user = userRepository.getById(userId);

        if(user.getIsEnabled()) {
            List<Device> devices = deviceRepository.findAllByUserId(userId);
            return new ResponseEntity<>(devices, HttpStatus.OK);
        }
        return new ResponseEntity<>("User is not enabled", HttpStatus.BAD_REQUEST);
    }
}
