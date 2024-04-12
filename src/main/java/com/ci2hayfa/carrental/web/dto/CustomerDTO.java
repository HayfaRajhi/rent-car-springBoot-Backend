package com.ci2hayfa.carrental.web.dto;

import com.ci2hayfa.carrental.dao.entities.Customer;

public record CustomerDTO(   
Long id,
String name,
String email,
String phone,
String image,
String birthday,
String licence_number
) {

    public static CustomerDTO toCustomerDTO(Customer customer){
        return new CustomerDTO(customer.getId(),customer.getName(),customer.getEmail(),customer.getPhone(),
        customer.getImage(),customer.getBirthday(),customer.getLicence_number());
    }

    public static Customer fromCustomerDTO(CustomerDTO customerDTO){
        return new Customer(customerDTO.id,customerDTO.name,customerDTO.email,
        customerDTO.phone,customerDTO.image,customerDTO.birthday,customerDTO.licence_number);
    }
}
