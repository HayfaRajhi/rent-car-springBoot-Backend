package com.ci2hayfa.carrental.business.services;

import java.util.List;

import com.ci2hayfa.carrental.dao.entities.Customer;
import com.ci2hayfa.carrental.exceptions.DuplicateCustomerException;

public interface CustomerService {

    public List<Customer> getAllCustomers();
    public Customer getCustomerById(Long id);
    public Customer createCustomer(Customer customer)throws DuplicateCustomerException;
    public Customer updateCustomer(Long id,Customer customer) throws DuplicateCustomerException;
    public void deleteCustomer(Long id);
}
