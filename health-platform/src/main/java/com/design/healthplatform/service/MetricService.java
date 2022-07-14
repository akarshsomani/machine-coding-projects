package com.design.healthplatform.service;

import com.design.healthplatform.dto.MetricRequest;
import com.design.healthplatform.model.Device;
import com.design.healthplatform.model.Metric;
import com.design.healthplatform.model.User;
import com.design.healthplatform.repository.DeviceRepository;
import com.design.healthplatform.repository.MetricRepository;
import com.design.healthplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class MetricService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MetricRepository metricRepository;

    public ResponseEntity<?> addMetric(Metric metric){
        Device device = deviceRepository.getById(metric.getDeviceId());
        if(device.getIsEnabled()){
            metric.setCreatedDate(new Date());
            metric = metricRepository.save(metric);
            return new ResponseEntity<>(metric, HttpStatus.OK);
        }
        return new ResponseEntity<>("Device is not enabled", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> getAllDeviceMetric(Long deviceId){
        Device device = deviceRepository.getById(deviceId);
        if(device.getIsEnabled()) {
            List<Metric> metric = metricRepository.findAllByDeviceId(deviceId);
            return new ResponseEntity<>(metric, HttpStatus.OK);
        }
        return new ResponseEntity<>("Device is not enabled", HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<?> getAllMetric(MetricRequest metricRequest) {

        User user = userRepository.getById(metricRequest.getUserId());
        if(user.getIsEnabled()) {

            List<Device> devices = deviceRepository.findAllByUserId(user.getUserId());

            List<Long> ids = new ArrayList<>();
            for (Device device : devices) {
                ids.add(device.getDeviceId());
            }

            List<Metric> metrics = metricRepository.findAllByDeviceIdIn(ids);

            List<Metric> filteredMetric = new ArrayList<>();

            for(Metric metric: metrics){
                if(metric.getCreatedDate().compareTo(metricRequest.getStartDate()) >=0 &&
                        metric.getCreatedDate().compareTo(metricRequest.getEndDate()) <=0){
                    filteredMetric.add(metric);
                }
            }
            return new ResponseEntity<>(filteredMetric, HttpStatus.OK);
        }
        return new ResponseEntity<>("User is not enabled", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> getAllMetricMax(MetricRequest metricRequest) {
        User user = userRepository.getById(metricRequest.getUserId());
        if(user.getIsEnabled()) {
            List<Device> devices = deviceRepository.findAllByUserId(user.getUserId());

            List<Long> ids = new ArrayList<>();
            for (Device device : devices) {
                ids.add(device.getDeviceId());
            }

            List<Metric> metrics = metricRepository.findAllByDeviceIdIn(ids);

            HashMap<String, Double> map = new HashMap<>();
            for (Metric metric : metrics) {
                if(metric.getCreatedDate().compareTo(metricRequest.getStartDate()) >=0 &&
                        metric.getCreatedDate().compareTo(metricRequest.getEndDate()) <=0) {
                    if (map.containsKey(metric.getMeasureType())) {
                        map.put(metric.getMeasureType(), Math.max(metric.getMeasureValue(), map.get(metric.getMeasureType())));
                    } else {
                        map.put(metric.getMeasureType(), metric.getMeasureValue());
                    }
                }
            }
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        return new ResponseEntity<>("User is not enabled", HttpStatus.BAD_REQUEST);
    }
}
