package com.design.healthplatform.model;

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
@Table(name = "Device")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceId;

    private String deviceName;

    private Long userId;

    private Date registeredDate;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean isEnabled = true;




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
