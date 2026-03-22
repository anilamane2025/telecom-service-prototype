package com.telecom.tsms.dto;

import com.telecom.tsms.enums.SubscriptionStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class SubscriptionResponse {

    private Long id;

    private Long customerId;
    private String customerName;

    private Long planId;
    private String planName;

    private LocalDate activationDate;
    private LocalDate expiryDate;

    private SubscriptionStatus status;


}
