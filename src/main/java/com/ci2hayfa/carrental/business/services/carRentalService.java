package com.ci2hayfa.carrental.business.services;

import java.util.List;

import com.ci2hayfa.carrental.dao.entities.CarRentalOperation;
import com.ci2hayfa.carrental.exceptions.DuplicateCarRentalOperationException;

public interface carRentalService {
    public List<CarRentalOperation> getAllOperations();
    public CarRentalOperation getCarRentalOpById(Long id);
    public CarRentalOperation createCustomer(CarRentalOperation carRentalOperation)throws DuplicateCarRentalOperationException;
    public CarRentalOperation updateCustomer(Long id,CarRentalOperation carRentalOperation) throws DuplicateCarRentalOperationException;
    public void deleteCustomer(Long id);
}
