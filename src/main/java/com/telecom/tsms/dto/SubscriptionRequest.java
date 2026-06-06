package com.telecom.tsms.dto;

import com.telecom.tsms.enums.SubscriptionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SubscriptionRequest {

    @NotNull(message = "Mobile Number ID is required")
    private Long mobileNumberId;

    @NotNull(message = "Plan ID is required")
    private Long planId;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "Status is required")
    private SubscriptionStatus status;

}