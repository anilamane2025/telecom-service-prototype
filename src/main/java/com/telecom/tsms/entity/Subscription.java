package com.telecom.tsms.entity;

import com.telecom.tsms.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "subscription")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="mobile_number_id", nullable = false)
    private MobileNumber mobileNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plan_id",nullable = false)
    private TelecomPlan telecomPlan;

    @Column(name = "start_date",nullable = false)
    private LocalDate startDate;

    @Column(name = "expiry_date",nullable = false)
    private LocalDate expiryDate;

    @Column(name = "last_recharge_date")
    private LocalDate lastRechargeDate;

    @Column(name = "total_recharge_count",nullable = false)
    private Integer totalRechargeCount;

    @Column(name = "auto_renew",nullable = false)
    private Boolean autoRenew;

    @Column(name = "current_plan_price",nullable = false,precision = 10, scale = 2)
    private BigDecimal currentPlanPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private SubscriptionStatus status;
}
