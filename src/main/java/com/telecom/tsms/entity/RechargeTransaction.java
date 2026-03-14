package com.telecom.tsms.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "recharge_transaction" )
public class RechargeTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plan_id", nullable = false)
    private TelecomPlan telecomPlan;

    @Column(nullable = false,precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name="payment_status",nullable = false,length = 20)
    private String paymentStatus;

    @Column(name = "transaction_ref",nullable = false,unique = true,length = 50)
    private String transactionRef;

    @Column(name = "transaction_date",nullable = false)
    private LocalDateTime transactionDate;
}