package com.ci2hayfa.carrental.business.servicesImpl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ci2hayfa.carrental.business.services.VehicleService;
import com.ci2hayfa.carrental.dao.entities.Vehicle;
import com.ci2hayfa.carrental.dao.repositories.VehicleRepository;
import com.ci2hayfa.carrental.exceptions.DuplicateVehicleException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
@Service
public class VehicleServiceImpl implements VehicleService{

    private final VehicleRepository vehicleRepository;
    public VehicleServiceImpl(VehicleRepository vehicleRepository){
        this.vehicleRepository=vehicleRepository;
    }
    @Override
    public List<Vehicle> getAllVehicles() {
        return this.vehicleRepository.findAll();
            // throw new UnsupportedOperationException("Unimplemented method 'getAllVehicles'");
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle)  throws  DuplicateVehicleException{
        try {
            return vehicleRepository.save(vehicle);
        } catch(Exception e){
  // Gérer les erreurs de contrainte d'unicité ici
  throw new  DuplicateVehicleException("A customer with the same email or other unique field already exists."+e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteVehicle(Long id) {
        try {
            if(vehicleRepository.existsById(id)){
                vehicleRepository.deleteById(id);
            }
            else {
                throw new EntityNotFoundException("Vehicle with id: " + id + " not found");
            }
        }
        catch (DataAccessException e){
            // Capturer toute exception liée à l'accès aux données (par exemple, violation
            // de contrainte de clé étrangère)
            throw new RuntimeException("Failed to delete Vehicle with id: " + id, e);
        }       
    }


    @Override
    public Vehicle getVehicleById(Long id) {
        return this.vehicleRepository.findById(id)
       .orElseThrow(() -> new EntityNotFoundException("Vehicle with id: " + id + " not found"));
}

@Override
@Transactional
public Vehicle updateVehicle(Long id, Vehicle Vehicle)  throws DuplicateVehicleException {
    // Check if the customer with the given ID exists
    if (vehicleRepository.existsById(id)) {
        try {
            // Set the ID of the customer object to ensure it updates the correct record
            Vehicle.setId(id);
            return vehicleRepository.save(Vehicle);
        } catch (DataIntegrityViolationException e) {
            // Handle data integrity violations (e.g., unique constraints) gracefully
            throw new  DuplicateVehicleException("A customer with the same email or other unique field already exists.");
        } catch (DataAccessException e) {
            // Handle general data access exceptions
            throw new RuntimeException("Failed to update Vehicle with id: " + id, e);
        }
    } else {
        // Throw an exception if the customer with the given ID does not exist
        throw new EntityNotFoundException("Vehicle with id: " + id + " not found");
    }
}

}
