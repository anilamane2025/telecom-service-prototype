package com.telecom.tsms.service;

import com.telecom.tsms.dto.RechargeRequest;
import com.telecom.tsms.dto.RechargeResponse;
import com.telecom.tsms.entity.*;
import com.telecom.tsms.exception.ResourceNotFoundException;
import com.telecom.tsms.repository.*;
import com.telecom.tsms.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RechargeServiceImpl implements RechargeService{

    private final RechargeTransactionRepository rechargeTransactionRepository;
    private final CustomerRepository customerRepository;
    private final TelecomPlanRepository telecomPlanRepository;

    private final SubscriptionRepository subscriptionRepository;

    private final UserRepository  userRepository;

    public RechargeServiceImpl(RechargeTransactionRepository rechargeTransactionRepository,
                               CustomerRepository customerRepository,
                               TelecomPlanRepository telecomPlanRepository,
                               SubscriptionRepository subscriptionRepository,
                               UserRepository userRepository){

        this.rechargeTransactionRepository = rechargeTransactionRepository;
        this.customerRepository = customerRepository;
        this.telecomPlanRepository = telecomPlanRepository;
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public RechargeResponse processRecharge(RechargeRequest request) {

        Subscription subscription = subscriptionRepository.findById(request.getSubscriptionId())
                .orElseThrow(()-> new ResourceNotFoundException(
                        "Subscription not found with id: "+request.getSubscriptionId()));


        RechargeTransaction transaction = RechargeTransaction.builder()
                .subscription(subscription)
                .amount(request.getAmount())
                .transactionStatus(request.getPaymentStatus())
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

    /*@Override
    public List<RechargeResponse> getRechargeTransactionByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with id: "+customerId
                ));
        return rechargeTransactionRepository.findByCustomer(customer)
                .stream()
                .map(this :: mapToResponse)
                .toList();
    }*/

    /*@Override
    public List<RechargeResponse> getMyRechargeHistory() {

        String username = SecurityUtils.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                "User not found with username: "+username
                        ));

        Customer customer = customerRepository
                .findByEmail(user.getEmail())
                .orElseThrow(()-> new ResourceNotFoundException(
                        "User not found with email: "+user.getEmail()
                ));

        return rechargeTransactionRepository
                .findByCustomer(customer)
                .stream()
                .map(this :: mapToResponse)
                .toList();
    }*/


    private RechargeResponse mapToResponse(RechargeTransaction transaction){

        Subscription subscription = transaction.getSubscription();

        return RechargeResponse.builder()
                .id(transaction.getId())
                .customerId(subscription.getMobileNumber().getCustomer().getId())
                .customerName(subscription.getMobileNumber().getCustomer().getFullName())
                .planId(subscription.getTelecomPlan().getId())
                .planName(subscription.getTelecomPlan().getPlanName())
                .amount(transaction.getAmount())
                .paymentStatus(transaction.getTransactionStatus())
                .transactionRef(transaction.getTransactionRef())
                .transactionDate(transaction.getTransactionDate())
                .build();
    }

}
