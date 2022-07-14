package com.design.healthplatform.repository;

import com.design.healthplatform.model.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetricRepository extends JpaRepository<Metric, Long> {

    List<Metric> findAllByDeviceId(Long deviceId);
    List<Metric> findAllByDeviceIdIn(List<Long> deviceIds);
}
