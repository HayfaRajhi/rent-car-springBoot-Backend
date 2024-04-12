package com.ci2hayfa.carrental.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ci2hayfa.carrental.dao.entities.CarRentalOperation;

public interface CarRentalOperationRepository  extends JpaRepository<CarRentalOperation,Long>{

}
