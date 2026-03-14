package com.telecom.tsms.repository;

import com.telecom.tsms.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByMobileNumber(String mobileNumber);
    Optional<Customer> findByCustomerCode(String customerCode);

}