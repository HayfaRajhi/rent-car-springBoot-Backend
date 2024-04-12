package com.ci2hayfa.carrental.business.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ci2hayfa.carrental.dao.entities.Vehicle;
import com.ci2hayfa.carrental.exceptions.DuplicateVehicleException;


public interface VehicleService {
    public List<Vehicle> getAllVehicles();
    public Vehicle getVehicleById(Long id);
    public Vehicle createVehicle(Vehicle vehicle) throws DuplicateVehicleException;//throws DuplicateCustomerException;
    public Vehicle updateVehicle(Long id,Vehicle vehicle) throws DuplicateVehicleException;//throws DuplicateCustomerException;
    public void deleteVehicle(Long id);
}
