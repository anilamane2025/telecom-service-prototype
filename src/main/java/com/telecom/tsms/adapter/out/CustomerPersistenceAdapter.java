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
    public Optional<com.telecom.tsms.domain.model.Customer> findById(Long id) {
        return customerRepository.findById(id)
                .map(this::mapToDomain);
    }

    private com.telecom.tsms.domain.model.Customer mapToDomain(Customer entity) {
        return com.telecom.tsms.domain.model.Customer.builder()
                .id(entity.getId())
                .customerCode(entity.getCustomerCode())
                .fullName(entity.getFullName())
                .email(entity.getEmail())
                .state(entity.getState())
                .kycStatus(entity.getKycStatus())
                .build();
    }

}
