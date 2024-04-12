package com.ci2hayfa.carrental.business.servicesImpl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ci2hayfa.carrental.business.services.CustomerService;
import com.ci2hayfa.carrental.dao.entities.Customer;
import com.ci2hayfa.carrental.dao.repositories.CustomerRepository;
import com.ci2hayfa.carrental.exceptions.DuplicateCustomerException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl  implements CustomerService{

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }
    @Override
    public Customer createCustomer(Customer customer)  throws DuplicateCustomerException{
        try {
            return customerRepository.save(customer);
        } catch(Exception e){
  // Gérer les erreurs de contrainte d'unicité ici
  throw new DuplicateCustomerException("A customer with the same email or other unique field already exists."+e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        try {
            if(customerRepository.existsById(id)){
                customerRepository.deleteById(id);
            }
            else {
                throw new EntityNotFoundException("Customer with id: " + id + " not found");
            }
        }
        catch (DataAccessException e){
            // Capturer toute exception liée à l'accès aux données (par exemple, violation
            // de contrainte de clé étrangère)
            throw new RuntimeException("Failed to delete customer with id: " + id, e);
        }       
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll (Sort.by(Direction.ASC,"name"));
    }

    @Override
    public Customer getCustomerById(Long id) {
        return this.customerRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Contact with id: " + id + " not found"));
}

@Override
@Transactional
public Customer updateCustomer(Long id, Customer customer) throws DuplicateCustomerException {
    // Check if the customer with the given ID exists
    if (customerRepository.existsById(id)) {
        try {
            // Set the ID of the customer object to ensure it updates the correct record
            customer.setId(id);
            return customerRepository.save(customer);
        } catch (DataIntegrityViolationException e) {
            // Handle data integrity violations (e.g., unique constraints) gracefully
            throw new DuplicateCustomerException("A customer with the same email or other unique field already exists.");
        } catch (DataAccessException e) {
            // Handle general data access exceptions
            throw new RuntimeException("Failed to update customer with id: " + id, e);
        }
    } else {
        // Throw an exception if the customer with the given ID does not exist
        throw new EntityNotFoundException("Customer with id: " + id + " not found");
    }
}



}
