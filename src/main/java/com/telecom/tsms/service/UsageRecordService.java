package com.telecom.tsms.service;

import com.telecom.tsms.dto.UsageRecordRequest;
import com.telecom.tsms.dto.UsageRecordResponse;
import com.telecom.tsms.enums.UsageCategory;
import com.telecom.tsms.enums.UsageType;

import java.util.List;

public interface UsageRecordService {
    UsageRecordResponse createUsageRecord(UsageRecordRequest usageRecordRequest);
    List<UsageRecordResponse> getAllUsageRecords();
    UsageRecordResponse getUsageRecordById(Long id);
    List<UsageRecordResponse> getUsageRecordsByMobileNumberId(Long mobileNumberId);
    List<UsageRecordResponse> getUsageRecordsBySubscriptionId(Long subscriptionId);
    List<UsageRecordResponse> getUsageRecordsByUsageType(UsageType usageType);
    List<UsageRecordResponse> getUsageRecordsByUsageCategory(UsageCategory usageCategory);
}