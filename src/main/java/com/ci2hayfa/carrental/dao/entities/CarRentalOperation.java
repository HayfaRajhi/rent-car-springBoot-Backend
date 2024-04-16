package com.ci2hayfa.carrental.dao.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carRentalOperations")
public class CarRentalOperation {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startDate;
    private Date endDate;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Vehicle vehicle;
    @Column(nullable = true)
    private String guaranteeType;
    @Column(nullable = true)

    private Double rentalFee;
    private Double totalPrice;

}
