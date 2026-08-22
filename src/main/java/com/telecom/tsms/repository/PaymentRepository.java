package com.telecom.tsms.repository;

import com.telecom.tsms.entity.Payment;
import com.telecom.tsms.enums.PaymentMode;
import com.telecom.tsms.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByRechargeTransaction_Id(Long rechargeTransactionId);

    List<Payment> findByPaymentStatus(PaymentStatus paymentStatus);

    List<Payment> findByPaymentMode(PaymentMode paymentMode);

    List<Payment> findByPaymentGateway(String paymentGateway);

    Optional<Payment> findByGatewayReference(String gatewayReference);

}