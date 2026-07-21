package com.telecom.tsms.dto;

import com.telecom.tsms.enums.UsageCategory;
import com.telecom.tsms.enums.UsageType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UsageRecordResponse {
    private Long id;
    private Long mobileNumberId;
    private String mobileNumber;
    private Long subscriptionId;
    private UsageType usageType;
    private UsageCategory usageCategory;
    private String applicationName;
    private Integer callMinutes;
    private Integer smsCount;
    private BigDecimal dataUsedMb;
    private LocalDateTime usageDateTime;
}