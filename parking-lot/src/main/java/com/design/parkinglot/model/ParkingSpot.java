package com.design.parkinglot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Table(name = "ParkingSpot")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkingSpotId;

    @Column(nullable = false)
    private String parkingSpotType;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean isEnabled = true;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean isOccupied = false;

    private Long parkingFloorId;

    private Double hourlyRate;

    private Integer maxMinParkPermitted;

    private Double penaltyPercentage;

    



//    @Temporal(TemporalType.TIMESTAMP)
//    @CreatedDate
//    @Column(nullable = false)
//    private Date createdDate;
//
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @LastModifiedDate
//    @Column(nullable = false)
//    private Date updatedDate;



}
