package com.telecom.tsms.service;

import com.telecom.tsms.dto.PaymentRequest;
import com.telecom.tsms.dto.PaymentResponse;
import com.telecom.tsms.enums.PaymentMode;
import com.telecom.tsms.enums.PaymentStatus;

import java.util.List;

public interface PaymentService{
    PaymentResponse createPayment(PaymentRequest request);
    List<PaymentResponse> getAllPayments();
    PaymentResponse getPaymentById(Long paymentId);
    PaymentResponse getPaymentByRechargeTransactionId(Long rechargeTransactionId);
    List<PaymentResponse> getPaymentsByPaymentStatus(PaymentStatus paymentStatus);
    List<PaymentResponse> getPaymentsByPaymentMode(PaymentMode paymentMode);
    List<PaymentResponse> getPaymentsByPaymentGateway(String paymentGateway);
    PaymentResponse getPaymentByGatewayReference(String gateWayReference);
}
