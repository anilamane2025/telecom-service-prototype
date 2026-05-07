package com.telecom.tsms.service;

import com.telecom.tsms.dto.RechargeRequest;
import com.telecom.tsms.dto.RechargeResponse;
import com.telecom.tsms.entity.Customer;
import com.telecom.tsms.entity.RechargeTransaction;
import com.telecom.tsms.entity.TelecomPlan;
import com.telecom.tsms.exception.ResourceNotFoundException;
import com.telecom.tsms.repository.CustomerRepository;
import com.telecom.tsms.repository.RechargeTransactionRepository;
import com.telecom.tsms.repository.TelecomPlanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RechargeServiceImpl implements RechargeService{

    private final RechargeTransactionRepository rechargeTransactionRepository;
    private final CustomerRepository customerRepository;
    private final TelecomPlanRepository telecomPlanRepository;

    public RechargeServiceImpl(RechargeTransactionRepository rechargeTransactionRepository,
                               CustomerRepository customerRepository,
                               TelecomPlanRepository telecomPlanRepository){

        this.rechargeTransactionRepository = rechargeTransactionRepository;
        this.customerRepository = customerRepository;
        this.telecomPlanRepository = telecomPlanRepository;

    }

    @Override
    public RechargeResponse processRecharge(RechargeRequest request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(()-> new ResourceNotFoundException(
                        "Customer not found with id: "+request.getCustomerId()));

        TelecomPlan plan = telecomPlanRepository.findById(request.getPlanId())
                .orElseThrow(()-> new ResourceNotFoundException(
                        "Plan not found with id: "+request.getPlanId()));

        RechargeTransaction transaction = RechargeTransaction.builder()
                .customer(customer)
                .telecomPlan(plan)
                .amount(request.getAmount())
                .paymentStatus(request.getPaymentStatus())
                .transactionRef(request.getTransactionRef())
                .transactionDate(LocalDateTime.now())
                .build();

        RechargeTransaction savedTransaction = rechargeTransactionRepository.save(transaction);

        return mapToResponse(savedTransaction);
    }

    @Override
    public List<RechargeResponse> getAllRechargeTransactions() {

        return rechargeTransactionRepository.findAll()
                .stream()
                .map(this :: mapToResponse)
                .toList();

    }

    @Override
    public RechargeResponse getRechargeTransactionById(Long id) {

        RechargeTransaction transaction = rechargeTransactionRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(
                        "Recharge Transaction not found with id: "+id
                ));

        return mapToResponse(transaction);
    }

    @Override
    public List<RechargeResponse> getRechargeTransactionByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with id: "+customerId
                ));
        return rechargeTransactionRepository.findByCustomer(customer)
                .stream()
                .map(this :: mapToResponse)
                .toList();
    }


    private RechargeResponse mapToResponse(RechargeTransaction transaction){
        return RechargeResponse.builder()
                .id(transaction.getId())
                .customerId(transaction.getCustomer().getId())
                .customerName(transaction.getCustomer().getFullName())
                .planId(transaction.getTelecomPlan().getId())
                .planName(transaction.getTelecomPlan().getPlanName())
                .amount(transaction.getAmount())
                .paymentStatus(transaction.getPaymentStatus())
                .transactionRef(transaction.getTransactionRef())
                .transactionDate(transaction.getTransactionDate())
                .build();
    }

}
