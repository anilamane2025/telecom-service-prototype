package com.telecom.tsms.adapter.out;

import com.telecom.tsms.entity.Customer;
import com.telecom.tsms.port.out.LoadCustomerPort;
import com.telecom.tsms.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerPersistenceAdapter implements LoadCustomerPort {

    private final CustomerRepository customerRepository;

    public CustomerPersistenceAdapter(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }
}
