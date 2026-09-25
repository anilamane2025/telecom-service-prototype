package com.telecom.tsms.service;

import com.telecom.tsms.dto.CustomerRequest;
import com.telecom.tsms.dto.CustomerResponse;
import com.telecom.tsms.entity.Customer;
import com.telecom.tsms.exception.ResourceNotFoundException;
import com.telecom.tsms.port.out.LoadCustomerPort;
import com.telecom.tsms.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final LoadCustomerPort loadCustomerPort;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               LoadCustomerPort loadCustomerPort){

        this.customerRepository = customerRepository;
        this.loadCustomerPort = loadCustomerPort;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        //without hexagonal

        /*Customer customer = mapToEntity(customerRequest);
        Customer savedCustomer = customerRepository.save(customer);
        return mapToResponse(savedCustomer);*/

        //with hexagonal

        com.telecom.tsms.domain.model.Customer customer = mapRequestToDomain(customerRequest);
        com.telecom.tsms.domain.model.Customer savedCustomer = loadCustomerPort.save(customer);
        return mapDomainToResponse(savedCustomer);
    }

    /*@Override
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this :: mapToResponse)
                .toList();
    }*/

    /*@Override
    public CustomerResponse getCustomerById(Long id) {
        Customer customer  = customerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("customer not found with id: "+id));
        return mapToResponse(customer);
    }*/

    public List<CustomerResponse> getAllCustomers() {
        List<com.telecom.tsms.domain.model.Customer> customers = loadCustomerPort.findAll();
        return customers.stream()
                .map(this :: mapDomainToResponse)
                .toList();
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        com.telecom.tsms.domain.model.Customer customer  = loadCustomerPort.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("customer not found with id: "+id));
        return mapDomainToResponse(customer);
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Customer id not found with id : "+id));

        existingCustomer.setCustomerCode(customerRequest.getCustomerCode());
        existingCustomer.setFullName(customerRequest.getFullName());
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
                .email(customer.getEmail())
                .state(customer.getState())
                .kycStatus(customer.getKycStatus())
                .build();
    }

    private CustomerResponse mapDomainToResponse(
            com.telecom.tsms.domain.model.Customer customer) {

        return CustomerResponse.builder()
                .id(customer.getId())
                .customerCode(customer.getCustomerCode())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .state(customer.getState())
                .kycStatus(customer.getKycStatus())
                .build();
    }

    private com.telecom.tsms.domain.model.Customer mapRequestToDomain(
            CustomerRequest request){
        return com.telecom.tsms.domain.model.Customer.builder()
                .customerCode(request.getCustomerCode())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .state(request.getState())
                .kycStatus(request.getKycStatus())
                .build();
    }
}
