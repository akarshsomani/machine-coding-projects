package com.design.healthplatform.controller;

import com.design.healthplatform.dto.MetricRequest;
import com.design.healthplatform.model.Device;
import com.design.healthplatform.model.Metric;
import com.design.healthplatform.model.User;
import com.design.healthplatform.service.DeviceService;
import com.design.healthplatform.service.MetricService;
import com.design.healthplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private MetricService metricService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user){
        return userService.createUser(user);
    }


    @PostMapping("/register-device")
    public ResponseEntity<?> registerDevice(@RequestBody Device device){
        return deviceService.registerDevice(device);
    }

    @GetMapping("/get-all-device")
    public ResponseEntity<?> getRegisteredDevices(@RequestParam Long userId){
        return deviceService.getAllRegisteredDevice(userId);
    }

    @PostMapping("/add-metric")
    public ResponseEntity<?> registerMetric(@RequestBody Metric metric){
        return metricService.addMetric(metric);
    }

    @GetMapping("/get-all-device-metric")
    public ResponseEntity<?> getAllDeviceMetric(@RequestParam Long deviceId){
        return metricService.getAllDeviceMetric(deviceId);
    }

    @PostMapping("/get-all-metric")
    public ResponseEntity<?> getAllMetric(@RequestBody MetricRequest metricRequest){
        return metricService.getAllMetric(metricRequest);
    }

    @PostMapping("/get-all-metric-max")
    public ResponseEntity<?> getAllMetricMax(@RequestBody MetricRequest metricRequest){
        return metricService.getAllMetricMax(metricRequest);
    }

}
