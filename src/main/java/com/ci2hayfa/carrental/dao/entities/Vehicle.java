package com.ci2hayfa.carrental.dao.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "vehicles")

public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique=true)
    private String registration;
    @Column(nullable = false)

    private String brand;
    @Column(nullable = false)

    private String model;
    @Column(nullable = false)
    private Double pricePerDay;

    @Column(nullable = true)
    private String image;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Date dateOfRegistration;
    @Column(nullable = false)

    private String color;
    
}
