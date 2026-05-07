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
    private Long customerId;
    private String customerName;
    private Long planId;
    private String planName;
    private BigDecimal amount;
    private String paymentStatus;
    private String transactionRef;
    private LocalDateTime transactionDate;
}
