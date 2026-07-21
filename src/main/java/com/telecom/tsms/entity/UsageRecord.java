package com.telecom.tsms.entity;

import com.telecom.tsms.enums.UsageCategory;
import com.telecom.tsms.enums.UsageType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "usage_record")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UsageRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "mobile_number_id",nullable = false)
    private MobileNumber mobileNumber;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "subscription_id",nullable = false)
    private Subscription subscription;

    @Enumerated(EnumType.STRING)
    @Column(name="usage_type",nullable = false, length = 10)
    private UsageType usageType;

    @Enumerated(EnumType.STRING)
    @Column(name = "usage_category",length = 30)
    private UsageCategory usageCategory;

    @Column(name="application_name",length = 100)
    private String applicationName;

    @Column(name = "call_minutes")
    private Integer callMinutes;

    @Column(name = "sms_count")
    private Integer smsCount;

    @Column(name = "data_used_mb",precision = 12, scale = 2)
    private BigDecimal dataUsedMb;

    @Column(name = "usage_date_time",nullable = false)
    private LocalDateTime usageDateTime;
}