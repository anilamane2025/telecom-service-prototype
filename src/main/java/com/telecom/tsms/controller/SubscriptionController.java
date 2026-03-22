package com.telecom.tsms.controller;

import com.telecom.tsms.dto.SubscriptionRequest;
import com.telecom.tsms.dto.SubscriptionResponse;
import com.telecom.tsms.service.SubscriptionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService){
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public ResponseEntity<SubscriptionResponse> createSubscription(@Valid @RequestBody SubscriptionRequest request){
        return ResponseEntity.ok(subscriptionService.createSubscription(request));
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionResponse>> getAllSubscriptions(){
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(subscriptionService.getSubscriptionById(id));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<SubscriptionResponse>> getByCustomerById(@PathVariable Long customerId){
        return ResponseEntity.ok(subscriptionService.getSubscriptionByCustomerId(customerId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubscription(@PathVariable Long id){
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.ok("Subscription is successfully deleted ");
    }
}
