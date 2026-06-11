package com.telecom.tsms.service;

import com.telecom.tsms.dto.MobileNumberRequest;
import com.telecom.tsms.dto.MobileNumberResponse;
import com.telecom.tsms.entity.Customer;
import com.telecom.tsms.entity.MobileNumber;
import com.telecom.tsms.exception.ResourceNotFoundException;
import com.telecom.tsms.repository.CustomerRepository;
import com.telecom.tsms.repository.MobileNumberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MobileNumberServiceImpl implements MobileNumberService{

    private MobileNumberRepository mobileNumberRepository;
    private CustomerRepository customerRepository;

    public MobileNumberServiceImpl(MobileNumberRepository mobileNumberRepository,
                                   CustomerRepository customerRepository){
        this.mobileNumberRepository = mobileNumberRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public MobileNumberResponse createMobileNumber(MobileNumberRequest request) {


        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with id: " + request.getCustomerId()));

        MobileNumber mobileNumber = MobileNumber.builder().customer(customer)
                .mobileNumber(request.getMobileNumber())
                .isPrimary(request.getPrimary())
                .status(request.getStatus())
                .build();

        MobileNumber saved = mobileNumberRepository.save(mobileNumber);

        return mapToResponse(saved);

    }

    public List<MobileNumberResponse> getAllMobileNumbers(){

        List<MobileNumber> mobileNumbers = mobileNumberRepository.findAll();
                return mobileNumbers.stream()
                .map(this :: mapToResponse)
                .toList();

    }

    public MobileNumberResponse getMobileNumberById(Long id){
        MobileNumber mobileNumber = mobileNumberRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(
                "MobileNumber not found with id" +id));
        return mapToResponse(mobileNumber);
    }

    public MobileNumberResponse updateMobileNumber(Long id, MobileNumberRequest request){
        MobileNumber existingMobileNumber = mobileNumberRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(
                        "MobileNumber not found for deletion with id"+id));

        existingMobileNumber.setMobileNumber(request.getMobileNumber());
        existingMobileNumber.setStatus(request.getStatus());
        existingMobileNumber.setPrimary(request.getPrimary());

        MobileNumber updatedMobileNumber = mobileNumberRepository.save(existingMobileNumber);
        return mapToResponse(updatedMobileNumber);
    }

    public void deleteMobileNumber(Long id){

        MobileNumber existingMobileNumber = mobileNumberRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Mobile number not found with id to delete" +id));
        mobileNumberRepository.delete(existingMobileNumber);
    }
    MobileNumberResponse mapToResponse(MobileNumber mobileNumber){
        return MobileNumberResponse.builder()
                .id(mobileNumber.getId())
                .customerId(mobileNumber.getCustomer().getId())
                .customerName(mobileNumber.getCustomer().getFullName())
                .mobileNumber(mobileNumber.getMobileNumber())
                .primary(mobileNumber.isPrimary())
                .status(mobileNumber.getStatus())
                .build();
    }
}
