package com.telecom.tsms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "recharge_transaction" )
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RechargeTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="subscription_id",nullable = false)
    private Subscription subscription;

    @Column(nullable = false,precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name="transaction_status",nullable = false,length = 20)
    private String transactionStatus;

    @Column(name = "transaction_ref",nullable = false,unique = true,length = 50)
    private String transactionRef;

    @Column(name = "transaction_date",nullable = false)
    private LocalDateTime transactionDate;
}