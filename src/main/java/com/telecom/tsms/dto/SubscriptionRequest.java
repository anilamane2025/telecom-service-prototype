package com.telecom.tsms.dto;

import com.telecom.tsms.enums.SubscriptionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SubscriptionRequest {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Plan ID is required")
    private Long planId;

    @NotNull(message = "Activation date is required")
    private LocalDate activationDate;

    @NotNull(message = "Status is required")
    private SubscriptionStatus status;


}