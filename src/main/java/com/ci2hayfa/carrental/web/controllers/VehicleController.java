package com.ci2hayfa.carrental.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ci2hayfa.carrental.business.services.VehicleService;
import com.ci2hayfa.carrental.dao.entities.Vehicle;
import com.ci2hayfa.carrental.exceptions.DuplicateVehicleException;
import com.ci2hayfa.carrental.web.dto.VehicleDTO;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;
    public VehicleController(VehicleService vehicleService){
        this.vehicleService=vehicleService;
    }

@GetMapping()
public ResponseEntity<?> getAllVehicles() {
    List<VehicleDTO> vehicleDTOs =this.vehicleService.getAllVehicles().stream()
    .map(VehicleDTO::toVehicleDTO).collect(Collectors.toList());
    return new ResponseEntity<>(vehicleDTOs,HttpStatus.OK);
}
@GetMapping("/{id}")
public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
    VehicleDTO customer =VehicleDTO.toVehicleDTO(this.vehicleService.getVehicleById(id));
    return new ResponseEntity<>(customer,HttpStatus.OK);
}

@PostMapping("/edit/{id}")
public ResponseEntity<?> addCustomer(@RequestBody VehicleDTO vehicleDTO) throws DuplicateVehicleException {
    Vehicle vehicle =VehicleDTO.fromVehicleDTO(vehicleDTO);        
    return new ResponseEntity<>(this.vehicleService.createVehicle(vehicle),HttpStatus.OK);
}
@PutMapping("/edit/{id}")
public ResponseEntity<?> updateContact(@PathVariable Long id, @RequestBody VehicleDTO vehicleDTO)
        throws DuplicateVehicleException {
    Vehicle vehicle = VehicleDTO.fromVehicleDTO(vehicleDTO);
    return new ResponseEntity<>(this.vehicleService.updateVehicle(id, vehicle), HttpStatus.OK);

}

@DeleteMapping("/{id}")
public ResponseEntity<?> deleteContact(@PathVariable Long id) {
    this.vehicleService.deleteVehicle(id);
    return new ResponseEntity<>( HttpStatus.NO_CONTENT);

}

}
