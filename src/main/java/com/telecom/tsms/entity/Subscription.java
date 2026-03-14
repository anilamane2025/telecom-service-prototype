package com.telecom.tsms.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plan_id",nullable = false)
    private TelecomPlan telecomPlan;

    @Column(name="activation_date",nullable = false)
    private LocalDate activationDate;

    @Column(nullable = false,length = 20)
    private String status;
}
