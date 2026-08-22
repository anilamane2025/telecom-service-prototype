package com.telecom.tsms.dto;

import com.telecom.tsms.enums.PaymentMode;
import com.telecom.tsms.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Setter
@Getter
public class PaymentResponse {
    private Long id;
    private Long rechargeTransactionId;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;
    private String paymentGateway;
    private BigDecimal paidAmount;
    private LocalDateTime paymentDate;
    private String gatewayReference;
}
