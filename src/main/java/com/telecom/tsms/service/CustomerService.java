package com.telecom.tsms.service;

import com.telecom.tsms.dto.CustomerRequest;
import com.telecom.tsms.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);
    List<CustomerResponse> getAllCustomers();

    CustomerResponse getCustomerById(Long id);

    CustomerResponse updateCustomer(Long id,CustomerRequest customerRequest);

    void deleleteCustomer(Long id);
}
