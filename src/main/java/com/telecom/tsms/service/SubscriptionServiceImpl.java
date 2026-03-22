package com.telecom.tsms.service;

import com.telecom.tsms.dto.SubscriptionRequest;
import com.telecom.tsms.dto.SubscriptionResponse;
import com.telecom.tsms.entity.Customer;
import com.telecom.tsms.entity.Subscription;
import com.telecom.tsms.entity.TelecomPlan;
import com.telecom.tsms.exception.ResourceNotFoundException;
import com.telecom.tsms.repository.CustomerRepository;
import com.telecom.tsms.repository.SubscriptionRepository;
import com.telecom.tsms.repository.TelecomPlanRepository;

import java.time.LocalDate;
import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService{


    private final SubscriptionRepository subscriptionRepository;
    private final CustomerRepository customerRepository;
    private final TelecomPlanRepository telecomPlanRepository;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                                   CustomerRepository customerRepository,
                                   TelecomPlanRepository telecomPlanRepository){
        this.subscriptionRepository = subscriptionRepository;
        this.customerRepository = customerRepository;
        this.telecomPlanRepository = telecomPlanRepository;
    }
    @Override
    public SubscriptionResponse createSubscription(SubscriptionRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(()->new ResourceNotFoundException("Customer not found with id: "+request.getCustomerId()));

        TelecomPlan plan = telecomPlanRepository.findById(request.getPlanId())
                .orElseThrow(()->new ResourceNotFoundException(("Plan not found with id"+request.getPlanId())));

        LocalDate expiryDate = request.getActivationDate().plusDays(plan.getValidityDays());

        Subscription subscription = Subscription.builder()
                .customer(customer)
                .telecomPlan(plan)
                .activationDate(request.getActivationDate())
                .expiryDate(expiryDate)
                .status(request.getStatus())
                .build();

        Subscription saved = subscriptionRepository.save(subscription);

        return mapToResponse(saved);
    }

    @Override
    public List<SubscriptionResponse> getAllSubscriptions() {
        return subscriptionRepository.findAll()
                .stream()
                .map(this :: mapToResponse)
                .toList();
    }

    @Override
    public SubscriptionResponse getSubscriptionById(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Subscription not found with id: "+id));
        return mapToResponse(subscription);
    }

    @Override
    public List<SubscriptionResponse> getSubscriptionByCustomerId(Long customerId) {
        return subscriptionRepository.findByCustomerId(customerId)
                .stream()
                .map(this :: mapToResponse)
                .toList();
    }

    @Override
    public void deleteSubscription(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Subscription not found with id: "+id));
        subscriptionRepository.delete(subscription);
    }

    private SubscriptionResponse mapToResponse(Subscription sub){
         return SubscriptionResponse.builder()
                 .id(sub.getId())
                 .customerId(sub.getCustomer().getId())
                 .customerName(sub.getCustomer().getFullName())
                 .planId(sub.getTelecomPlan().getId())
                 .planName(sub.getTelecomPlan().getPlanName())
                 .activationDate(sub.getActivationDate())
                 .expiryDate(sub.getExpiryDate())
                 .status(sub.getStatus())
                 .build();
    }
}