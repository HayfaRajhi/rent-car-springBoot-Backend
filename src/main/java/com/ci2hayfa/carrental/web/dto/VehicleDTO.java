package com.ci2hayfa.carrental.web.dto;

import java.util.Date;
import java.util.List;

import com.ci2hayfa.carrental.dao.entities.Vehicle;

public record VehicleDTO(
    Long id,
    String registration,
     String brand,

     String model,
     Double pricePerDay,

     String image,

     String status,

     Date dateOfRegistration,

     String color
) {

    public static VehicleDTO toVehicleDTO(Vehicle vehicle){
        return new VehicleDTO(vehicle.getId(), 
                              vehicle.getRegistration(), 
                              vehicle.getBrand(), 
                              vehicle.getModel(),
                               vehicle.getPricePerDay(), 
                               vehicle.getImage(), 
                               vehicle.getStatus(), 
                               vehicle.getDateOfRegistration(), 
                               vehicle.getColor());
    }
    public static Vehicle fromVehicleDTO(VehicleDTO vehicleDTO){
        return new Vehicle(vehicleDTO.id,vehicleDTO.registration,vehicleDTO.brand
        ,vehicleDTO.model,vehicleDTO.pricePerDay,vehicleDTO.image,vehicleDTO.status,
        vehicleDTO.dateOfRegistration,vehicleDTO.color);
    }
}
