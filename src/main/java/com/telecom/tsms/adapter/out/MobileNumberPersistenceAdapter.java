package com.telecom.tsms.adapter.out;

import com.telecom.tsms.domain.model.MobileNumber;
import com.telecom.tsms.exception.ResourceNotFoundException;
import com.telecom.tsms.port.out.MobileNumberPort;
import com.telecom.tsms.repository.CustomerRepository;
import com.telecom.tsms.repository.MobileNumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MobileNumberPersistenceAdapter implements MobileNumberPort {

    private final MobileNumberRepository mobileNumberRepository;
    private final CustomerRepository customerRepository;

    @Override
    public MobileNumber save(MobileNumber mobileNumber) {

        com.telecom.tsms.entity.MobileNumber entity = new com.telecom.tsms.entity.MobileNumber();

        entity.setMobileNumber(mobileNumber.getMobileNumber());
        entity.setPrimary(mobileNumber.isPrimary());
        entity.setStatus(mobileNumber.getStatus());

        com.telecom.tsms.entity.Customer customer = customerRepository.findById(mobileNumber.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with ID: "+mobileNumber.getCustomerId()
                ));
        entity.setCustomer(customer);
        com.telecom.tsms.entity.MobileNumber savedEntity = mobileNumberRepository.save(entity);
        return MobileNumber.builder()
                .id(savedEntity.getId())
                .mobileNumber(savedEntity.getMobileNumber())
                .isPrimary(savedEntity.isPrimary())
                .status(savedEntity.getStatus())
                .customerId(savedEntity.getCustomer().getId())
                .build();
    }

    @Override
    public Optional<MobileNumber> findByMobileNumber(String mobileNumber) {
        return mobileNumberRepository.findByMobileNumber(mobileNumber)
                .map(entity -> MobileNumber.builder()
                        .id(entity.getId()).mobileNumber(entity.getMobileNumber())
                        .isPrimary(entity.isPrimary())
                        .status(entity.getStatus())
                        .customerId(entity.getCustomer().getId())
                        .build());
    }
}
