package com.telecom.tsms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="payment")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="recharge_transaction_id",nullable = false)
    private RechargeTransaction rechargeTransaction;

    @Column(name="payment_mode",nullable = false)
    private String paymentMode;

    @Column(name="payment_status",nullable = false)
    private String paymentStatus;

    @Column(name="payment_gateway",nullable = false)
    private String paymentGateway;

    @Column(name="paid_amount",nullable = false)
    private BigDecimal paidAmount;

    @Column(name="payment_date",nullable = false)
    private LocalDateTime paymentDate;

    @Column(name="gateway_reference",nullable = false,unique = true)
    private String gatewayReference;

}
