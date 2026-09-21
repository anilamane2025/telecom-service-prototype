package com.telecom.tsms.port.out;

import com.telecom.tsms.domain.model.Customer;

import java.util.Optional;

public interface LoadCustomerPort {
    Optional<Customer> findById(Long id);
}
