package com.telecom.tsms.dto;

import com.telecom.tsms.enums.PaymentMode;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PaymentRequest {
    @NotNull
    private Long rechargeTransactionId;
    private PaymentMode paymentMode;
    private String paymentGateway;
}