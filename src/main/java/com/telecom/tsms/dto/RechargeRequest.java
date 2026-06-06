package com.telecom.tsms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RechargeRequest {


    @NotNull(message = "Subscription ID is required")
    private Long subscriptionId;

    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    @NotNull(message = "Payment status required")
    private String paymentStatus;

    @NotNull(message = "Transaction reference is required")
    private String transactionRef;

}