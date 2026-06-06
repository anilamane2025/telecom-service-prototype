package com.telecom.tsms.controller;

import com.telecom.tsms.dto.SubscriptionRequest;
import com.telecom.tsms.dto.SubscriptionResponse;
import com.telecom.tsms.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Subscription APIs",description = "Operations related to subscription")
@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService){
        this.subscriptionService = subscriptionService;
    }

    @Operation(summary = "Create subscription")
    @PostMapping
    public ResponseEntity<SubscriptionResponse> createSubscription(@Valid @RequestBody SubscriptionRequest request){
        return ResponseEntity.ok(subscriptionService.createSubscription(request));
    }

    @Operation(summary = "Get all subscription")
    @GetMapping
    public ResponseEntity<List<SubscriptionResponse>> getAllSubscriptions(){
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
    }

    @Operation(summary = "Get subscription by ID")
    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(subscriptionService.getSubscriptionById(id));
    }

    /*@Operation(summary = "Get subscription by Customer ID")
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<SubscriptionResponse>> getByCustomerById(@PathVariable Long customerId){
        return ResponseEntity.ok(subscriptionService.getSubscriptionByCustomerId(customerId));
    }*/

    @Operation(summary = "Delete subscription by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubscription(@PathVariable Long id){
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.ok("Subscription is successfully deleted ");
    }

    /*@GetMapping("/my")
    public ResponseEntity<List<SubscriptionResponse>> getMySubscriptions(){
            return ResponseEntity.ok(subscriptionService.getMySubscriptions()
        );
    }*/
}
