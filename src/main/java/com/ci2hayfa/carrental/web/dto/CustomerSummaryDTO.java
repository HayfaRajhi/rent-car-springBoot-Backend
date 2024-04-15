package com.ci2hayfa.carrental.web.dto;

import com.ci2hayfa.carrental.dao.entities.Customer;

public record CustomerSummaryDTO(
        Long id,
        String name,
        String email) {

    public static CustomerSummaryDTO toCustomerSummaryDTO(Customer customer) {
        return new CustomerSummaryDTO(customer.getId(), customer.getName(), customer.getEmail());
    }

}
