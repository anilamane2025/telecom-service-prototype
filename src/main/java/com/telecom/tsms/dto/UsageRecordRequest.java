package com.telecom.tsms.dto;

import com.telecom.tsms.enums.UsageCategory;
import com.telecom.tsms.enums.UsageType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UsageRecordRequest {

    @NotNull(message = "Subscription Id is required")
    private Long subscriptionId;

    @NotNull(message = "Usage type is required")
    private UsageType usageType;

    private Integer callMinutes;
    private Integer smsCount;
    private BigDecimal dataUsedMb;
    private UsageCategory usageCategory;
    private String applicationName;
}