package com.design.healthplatform.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetricRequest implements Serializable {


    private Long userId;

    private Date startDate;

    private Date endDate;



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
