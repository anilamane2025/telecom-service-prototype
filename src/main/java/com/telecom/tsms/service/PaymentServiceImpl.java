package com.telecom.tsms.service;

import com.telecom.tsms.dto.PaymentRequest;
import com.telecom.tsms.dto.PaymentResponse;
import com.telecom.tsms.entity.Payment;
import com.telecom.tsms.entity.RechargeTransaction;
import com.telecom.tsms.enums.PaymentMode;
import com.telecom.tsms.enums.PaymentStatus;
import com.telecom.tsms.exception.PaymentAlreadyExistsException;
import com.telecom.tsms.exception.ResourceNotFoundException;
import com.telecom.tsms.repository.PaymentRepository;
import com.telecom.tsms.repository.RechargeTransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService{


    private final PaymentRepository paymentRepository;

    private final RechargeTransactionRepository rechargeTransactionRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              RechargeTransactionRepository rechargeTransactionRepository){
        this.paymentRepository = paymentRepository;
        this.rechargeTransactionRepository = rechargeTransactionRepository;
    }

    @Override
    public PaymentResponse createPayment(PaymentRequest request) {

        RechargeTransaction rechargeTransaction = rechargeTransactionRepository
                .findById(request.getRechargeTransactionId())
                .orElseThrow(() -> new ResourceNotFoundException("Recharge transaction not found with id: "
                + request.getRechargeTransactionId()
                ));

        Optional<Payment> existingPayment = paymentRepository.findByRechargeTransaction_Id(request.getRechargeTransactionId());
        if(existingPayment.isPresent()){
            throw new PaymentAlreadyExistsException(
                    "Payment already exists for recharge transaction id: "
                            + request.getRechargeTransactionId()
            );
        }

        Payment payment = new Payment();
        payment.setPaymentMode(request.getPaymentMode());
        payment.setRechargeTransaction(rechargeTransaction);
        payment.setPaymentGateway(request.getPaymentGateway());
        payment.setPaidAmount(rechargeTransaction.getAmount());
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setGatewayReference("PAY-" + UUID.randomUUID());

        Payment saved  = paymentRepository.save(payment);

        return mapToResponse(saved);
    }

    private PaymentResponse mapToResponse(Payment payment) {

        return PaymentResponse.builder()
                .id(payment.getId())
                .rechargeTransactionId(payment.getRechargeTransaction().getId())
                .paymentStatus(payment.getPaymentStatus())
                .paymentMode(payment.getPaymentMode())
                .paidAmount(payment.getPaidAmount())
                .paymentDate(payment.getPaymentDate())
                .paymentGateway(payment.getPaymentGateway())
                .gatewayReference(payment.getGatewayReference())
                .build();

    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public PaymentResponse getPaymentById(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: "+paymentId));
        return mapToResponse(payment);
    }

    @Override
    public PaymentResponse getPaymentByRechargeTransactionId(Long rechargeTransactionId) {
        Payment payment = paymentRepository.findByRechargeTransaction_Id(rechargeTransactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with recharge transaction Id: "+rechargeTransactionId));
        return mapToResponse(payment);
    }

    @Override
    public List<PaymentResponse> getPaymentsByPaymentStatus(PaymentStatus paymentStatus) {
        return paymentRepository.findByPaymentStatus(paymentStatus)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<PaymentResponse> getPaymentsByPaymentMode(PaymentMode paymentMode) {
        return paymentRepository.findByPaymentMode(paymentMode)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<PaymentResponse> getPaymentsByPaymentGateway(String paymentGateway) {
        return paymentRepository.findByPaymentGateway(paymentGateway)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public PaymentResponse getPaymentByGatewayReference(String gatewayReference) {
        Payment payment = paymentRepository.findByGatewayReference(gatewayReference)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with gateway reference: "+gatewayReference));
        return mapToResponse(payment);
    }
}
