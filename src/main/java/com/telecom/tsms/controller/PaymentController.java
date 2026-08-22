package com.telecom.tsms.controller;

import com.telecom.tsms.dto.PaymentRequest;
import com.telecom.tsms.dto.PaymentResponse;
import com.telecom.tsms.entity.Payment;
import com.telecom.tsms.enums.PaymentMode;
import com.telecom.tsms.enums.PaymentStatus;
import com.telecom.tsms.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Payment APIs", description = "Operation related to Payments")
@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @Operation(summary = "Create Payment")
    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@Valid @RequestBody PaymentRequest paymentRequest){
        PaymentResponse createdPayment = paymentService.createPayment(paymentRequest);
        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all payments")
    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAllPayments(){
        List<PaymentResponse> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @Operation(summary = "Get payment by id")
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Long paymentId){
        PaymentResponse payment = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(payment);
    }

    @Operation(summary = "Get payment by recharge transaction id")
    @GetMapping("/recharge/{rechargeTransactionId}")
    public ResponseEntity<PaymentResponse> getPaymentByRechargeTransactionId(@PathVariable Long rechargeTransactionId){
        PaymentResponse payment = paymentService.getPaymentByRechargeTransactionId(rechargeTransactionId);
        return ResponseEntity.ok(payment);
    }

    @Operation(summary = "Get payment by status")
    @GetMapping("/status/{paymentStatus}")
    public ResponseEntity<List<PaymentResponse>> getPaymentsByPaymentStatus(@PathVariable PaymentStatus paymentStatus){
        List<PaymentResponse> payments = paymentService.getPaymentsByPaymentStatus(paymentStatus);
        return ResponseEntity.ok(payments);
    }

    @Operation(summary = "Get payment by payment mode")
    @GetMapping("/mode/{paymentMode}")
    public ResponseEntity<List<PaymentResponse>> getPaymentsByPaymentMode(@PathVariable PaymentMode paymentMode){
        List<PaymentResponse> payments = paymentService.getPaymentsByPaymentMode(paymentMode);
        return ResponseEntity.ok(payments);
    }

    @Operation(summary = "Get payment by payment gateway")
    @GetMapping("/gateway/{paymentGateway}")
    public ResponseEntity<List<PaymentResponse>> getPaymentsByPaymentGateway(@PathVariable String paymentGateway){
        List<PaymentResponse> payments = paymentService.getPaymentsByPaymentGateway(paymentGateway);
        return ResponseEntity.ok(payments);
    }

    @Operation(summary = "Get payment by gateway reference")
    @GetMapping("/reference/{gatewayReference}")
    public ResponseEntity<PaymentResponse> getPaymentByGatewayReference(@PathVariable String gatewayReference){
        PaymentResponse payment = paymentService.getPaymentByGatewayReference(gatewayReference);
        return ResponseEntity.ok(payment);
    }

}