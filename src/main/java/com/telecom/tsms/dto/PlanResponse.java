package com.telecom.tsms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class PlanResponse {

    private Long id;
    private String planCode;
    private String planName;
    private String planType;
    private Integer validityDays;
    private BigDecimal price;
    private Double dataLimitGb;
    private Integer smsLimit;
    private Integer voiceLimitMinutes;
    private Boolean active;


}
