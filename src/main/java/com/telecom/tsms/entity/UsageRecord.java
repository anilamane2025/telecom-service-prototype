package com.telecom.tsms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "subscription_id",nullable = false,unique = true)
    private Subscription subscription;

    @Column(name = "used_data_mb")
    private Double usedDataMb;

    @Column(name = "used_sms_count")
    private Integer usedSmsCount;

    @Column(name = "used_voice_minutes")
    private Integer usedVoiceMinutes;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
}
