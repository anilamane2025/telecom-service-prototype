package com.telecom.tsms.service;

import com.telecom.tsms.dto.SubscriptionRequest;
import com.telecom.tsms.dto.SubscriptionResponse;
import com.telecom.tsms.entity.Subscription;
import com.telecom.tsms.repository.CustomerRepository;
import com.telecom.tsms.repository.SubscriptionRepository;
import com.telecom.tsms.repository.TelecomPlanRepository;

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
        return null;
    }

    @Override
    public List<SubscriptionResponse> getAllSubscriptions() {
        return null;
    }

    @Override
    public SubscriptionResponse getSubscriptionById(Long id) {
        return null;
    }

    @Override
    public List<SubscriptionResponse> getSubscriptionByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public void deleteSubscription(Long id) {

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