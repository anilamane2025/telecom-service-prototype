package com.telecom.tsms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class RechargeResponse {

    private Long id;

    private Long subscriptionId;
    private String mobileNumber;
    private String planName;

    private BigDecimal amount;
    private String transactionStatus;
    private String transactionRef;
    private LocalDateTime transactionDate;
}