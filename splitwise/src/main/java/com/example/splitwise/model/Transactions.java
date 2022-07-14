package com.example.splitwise.model;

import com.example.splitwise.enums.SharingType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Table(name = "transactions")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @NotNull
    private String sharingType;

    @NonNull
    private Double totalAmount;

    @NotNull
    private Double splitAmount;

    @NotNull
    private Long paidBy;

    @NotNull
    private Long paidTo;

    @NotNull
    private Long friendshipId;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(nullable = false)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedDate;

    private Double splitPercentage;

    private Integer splitAmongCount;

    private Double splitExactAmount;


}
