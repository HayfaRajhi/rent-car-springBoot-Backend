package com.ci2hayfa.carrental.business.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ci2hayfa.carrental.business.services.carRentalService;
import com.ci2hayfa.carrental.dao.entities.CarRentalOperation;
import com.ci2hayfa.carrental.exceptions.DuplicateCarRentalOperationException;

@Service
public class CarRentalServiceImpl implements carRentalService {

    @Override
    public List<CarRentalOperation> getAllOperations() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllOperations'");
    }

    @Override
    public CarRentalOperation getCarRentalOpById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCarRentalOpById'");
    }

    @Override
    public CarRentalOperation createCustomer(CarRentalOperation carRentalOperation)
            throws DuplicateCarRentalOperationException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCustomer'");
    }

    @Override
    public CarRentalOperation updateCustomer(Long id, CarRentalOperation carRentalOperation)
            throws DuplicateCarRentalOperationException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCustomer'");
    }

    @Override
    public void deleteCustomer(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCustomer'");
    }

}
