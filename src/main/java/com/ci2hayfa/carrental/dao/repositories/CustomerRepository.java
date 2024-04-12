package com.ci2hayfa.carrental.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ci2hayfa.carrental.dao.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{

}
