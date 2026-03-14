package com.telecom.tsms.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.apachecommons.CommonsLog;

import java.math.BigDecimal;

@Entity
@Table(name="telecom_plan")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TelecomPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plan_code",nullable = false, length = 30)
    private String planCode;

    @Column(name = "plan_name", nullable = false, length = 100)
    private String planName;

    @Column(name = "plan_type", nullable = false, length = 30)
    private String planType;

    @Column(name = "validity_days" ,nullable = false)
    private Integer validityDays;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal price;

    @Column(name = "data_limit_gb")
    private Double dataLimitGb;

    @Column(name = "sms_limit")
    private Integer smsLimit;

    @Column(name = "voice_limit_minutes")
    private Integer voiceLimitMinutes;

    @Column(nullable = false)
    private Boolean active;

}
