package com.ci2hayfa.carrental.web.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.ci2hayfa.carrental.business.services.CustomerService;
import com.ci2hayfa.carrental.dao.entities.Customer;
import com.ci2hayfa.carrental.exceptions.DuplicateCustomerException;
import com.ci2hayfa.carrental.web.dto.CustomerDTO;
import com.ci2hayfa.carrental.web.dto.CustomerSummaryDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllCustomers() {
        List<CustomerDTO> customers = this.customerService.getAllCustomers()
        .stream()
        .map(CustomerDTO::toCustomerDTO)
        .collect(Collectors.toList()); 
        /*List<CustomerSummaryDTO> customers = this.customerService.getAllCustomers()
        .stream()
        .map(CustomerSummaryDTO::toCustomerSummaryDTO)
        .collect(Collectors.toList()); */   
        return new ResponseEntity<>(customers, HttpStatus.OK);

                // .map(contact->ContactSummaryDTO.toContactSummaryDTO(contact))

        /*  List<CustomerDTO> customerDTOs = customers.stream()
                                                 .map(CustomerDTO::toCustomerDTO)
                                                 .collect(Collectors.toList());*/
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        CustomerDTO customer =CustomerDTO.toCustomerDTO(this.customerService.getCustomerById(id));
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDTO customerDTO) throws DuplicateCustomerException {
        Customer customer =CustomerDTO.fromCustomerDTO(customerDTO);        
        return new ResponseEntity<>(this.customerService.createCustomer(customer),HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateContact(@PathVariable Long id, @RequestBody CustomerDTO customerDTO)
            throws DuplicateCustomerException {
        Customer customer = CustomerDTO.fromCustomerDTO(customerDTO);
        return new ResponseEntity<>(this.customerService.updateCustomer(id, customer), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable Long id) {
        this.customerService.deleteCustomer(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);

    }
}
