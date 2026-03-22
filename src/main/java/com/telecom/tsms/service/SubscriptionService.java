package com.telecom.tsms.service;


import com.telecom.tsms.dto.SubscriptionRequest;
import com.telecom.tsms.dto.SubscriptionResponse;

import java.util.List;

public interface SubscriptionService {

    SubscriptionResponse createSubscription(SubscriptionRequest request);

    List<SubscriptionResponse> getAllSubscriptions();

    SubscriptionResponse getSubscriptionById(Long id);
    List<SubscriptionResponse> getSubscriptionByCustomerId(Long customerId);

    void deleteSubscription(Long id);

}
