package com.telecom.tsms.service;

import com.telecom.tsms.dto.SubscriptionRequest;
import com.telecom.tsms.dto.SubscriptionResponse;
import com.telecom.tsms.entity.*;
import com.telecom.tsms.exception.ResourceNotFoundException;
import com.telecom.tsms.repository.*;
import com.telecom.tsms.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{


    private final SubscriptionRepository subscriptionRepository;
    private final TelecomPlanRepository telecomPlanRepository;

    private final MobileNumberRepository mobileNumberRepository;
    private final UserRepository userRepository;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                                   TelecomPlanRepository telecomPlanRepository,
                                   UserRepository userRepository,
                                   MobileNumberRepository mobileNumberRepository){
        this.subscriptionRepository = subscriptionRepository;
        this.telecomPlanRepository = telecomPlanRepository;
        this.userRepository = userRepository;
        this.mobileNumberRepository = mobileNumberRepository;
    }
    @Override
    public SubscriptionResponse createSubscription(SubscriptionRequest request) {
        /*Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(()->new ResourceNotFoundException("Customer not found with id: "+request.getCustomerId()));*/

        MobileNumber mobileNumber = mobileNumberRepository.findById(request.getMobileNumberId())
                .orElseThrow(()->new ResourceNotFoundException("Mobile Number not found with id: "+request.getMobileNumberId()));

        TelecomPlan plan = telecomPlanRepository.findById(request.getPlanId())
                .orElseThrow(()->new ResourceNotFoundException(("Plan not found with id"+request.getPlanId())));

        //LocalDate expiryDate = request.getActivationDate().plusDays(plan.getValidityDays());
        LocalDate expiryDate = request.getStartDate().plusDays(plan.getValidityDays());

        Subscription subscription = Subscription.builder()
                .mobileNumber(mobileNumber)
                .telecomPlan(plan)
                .startDate(request.getStartDate())
                .expiryDate(expiryDate)
                .status(request.getStatus())
                .totalRechargeCount(0)
                .autoRenew(false)
                .currentPlanPrice(plan.getPrice())
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

    /*@Override
    public List<SubscriptionResponse> getSubscriptionByCustomerId(Long customerId) {
        return subscriptionRepository.findByCustomerId(customerId)
                .stream()
                .map(this :: mapToResponse)
                .toList();
    }*/

    /*@Override
    public List<SubscriptionResponse> getMySubscriptions() {

        String username = SecurityUtils.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(()->
                        new ResourceNotFoundException("User not found with username: "+username));

        *//*Customer customer = customerRepository.findByEmail(user.getEmail())
                .orElseThrow(()->
                        new ResourceNotFoundException("Customer not found with eamil : "+user.getEmail())
                );*//*

        MobileNumber mobileNumber = mobileNumberRepository.findById(user.getId())
                .orElseThrow(()->
                        new ResourceNotFoundException("Mobile Number not found with id : "+user.getId())
                );

        return subscriptionRepository.findByCustomer(mobileNumber)
                .stream()
                .map(this :: mapToResponse)
                .toList();


    }*/

    @Override
    public void deleteSubscription(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Subscription not found with id: "+id));
        subscriptionRepository.delete(subscription);
    }

    private SubscriptionResponse mapToResponse(Subscription sub){
         return SubscriptionResponse.builder()
                 .id(sub.getId())
                 .customerId(sub.getMobileNumber().getCustomer().getId())
                 .customerName(sub.getMobileNumber().getCustomer().getFullName())
                 .planId(sub.getTelecomPlan().getId())
                 .planName(sub.getTelecomPlan().getPlanName())
                 .activationDate(sub.getStartDate())
                 .expiryDate(sub.getExpiryDate())
                 .status(sub.getStatus())
                 .build();
    }
}