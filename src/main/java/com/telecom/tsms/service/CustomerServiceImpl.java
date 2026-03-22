package com.telecom.tsms.service;

import com.telecom.tsms.dto.CustomerRequest;
import com.telecom.tsms.dto.CustomerResponse;
import com.telecom.tsms.entity.Customer;
import com.telecom.tsms.exception.ResourceNotFoundException;
import com.telecom.tsms.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = mapToEntity(customerRequest);
        Customer savedCustomer = customerRepository.save(customer);
        return mapToResponse(savedCustomer);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this :: mapToResponse)
                .toList();
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        Customer customer  = customerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("customer not found with id: "+id));
        return mapToResponse(customer);
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Customer id not found with id : "+id));

        existingCustomer.setCustomerCode(customerRequest.getCustomerCode());
        existingCustomer.setFullName(customerRequest.getFullName());
        existingCustomer.setMobileNumber(customerRequest.getMobileNumber());
        existingCustomer.setState(customerRequest.getState());
        existingCustomer.setEmail(customerRequest.getEmail());
        existingCustomer.setKycStatus(customerRequest.getKycStatus());

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return mapToResponse(updatedCustomer);
    }

    @Override
    public void deleleteCustomer(Long id) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Customer not found with Id "+id));

        customerRepository.delete(existingCustomer);
    }

    private Customer mapToEntity(CustomerRequest customerRequest){
        return Customer.builder()
                .customerCode(customerRequest.getCustomerCode())
                .fullName(customerRequest.getFullName())
                .email(customerRequest.getEmail())
                .state(customerRequest.getState())
                .kycStatus(customerRequest.getKycStatus())
                .build();
    }

    private CustomerResponse mapToResponse(Customer customer){
        return CustomerResponse.builder()
                .id(customer.getId())
                .customerCode(customer.getCustomerCode())
                .fullName(customer.getFullName())
                .mobileNumber(customer.getMobileNumber())
                .email(customer.getEmail())
                .state(customer.getState())
                .kycStatus(customer.getKycStatus())
                .build();
    }
}
