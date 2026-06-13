package com.telecom.tsms.dto;

import com.telecom.tsms.enums.SubscriptionStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class SubscriptionResponse {

    private Long id;

    private Long mobileNumberId;
    private String mobileNumber;

    private Long planId;
    private String planName;

    private LocalDate startDate;
    private LocalDate expiryDate;

    private SubscriptionStatus status;

    private BigDecimal currentPlanPrice;
}
