package com.telecom.tsms.service;

import com.telecom.tsms.dto.UsageRecordRequest;
import com.telecom.tsms.dto.UsageRecordResponse;
import com.telecom.tsms.entity.MobileNumber;
import com.telecom.tsms.entity.Subscription;
import com.telecom.tsms.entity.UsageRecord;
import com.telecom.tsms.enums.UsageCategory;
import com.telecom.tsms.enums.UsageType;
import com.telecom.tsms.exception.ResourceNotFoundException;
import com.telecom.tsms.repository.SubscriptionRepository;
import com.telecom.tsms.repository.UsageRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsageRecordServiceImpl implements UsageRecordService {

    private final UsageRecordRepository usageRecordRepository;
    private final SubscriptionRepository subscriptionRepository;

    public UsageRecordServiceImpl(UsageRecordRepository usageRecordRepository,
                          SubscriptionRepository subscriptionRepository){
        this.usageRecordRepository = usageRecordRepository;
        this.subscriptionRepository = subscriptionRepository;
    }
    @Override
    public UsageRecordResponse createUsageRecord(UsageRecordRequest usageRecordRequest) {

        Subscription subscription = subscriptionRepository.findById(usageRecordRequest.getSubscriptionId())
                .orElseThrow(()->new ResourceNotFoundException("Subscription not found with id: "+usageRecordRequest.getSubscriptionId()));

        validateUsageRecordRequest(usageRecordRequest);
        MobileNumber mobileNumber = subscription.getMobileNumber();

        UsageRecord usageRecord = UsageRecord.builder()
                .mobileNumber(mobileNumber)
                .subscription(subscription)
                .usageType(usageRecordRequest.getUsageType())
                .usageCategory(usageRecordRequest.getUsageCategory())
                .applicationName(usageRecordRequest.getApplicationName())
                .callMinutes(usageRecordRequest.getCallMinutes())
                .smsCount(usageRecordRequest.getSmsCount())
                .dataUsedMb(usageRecordRequest.getDataUsedMb())
                .usageDateTime(LocalDateTime.now())
                .build();

        UsageRecord savedUsageRecord = usageRecordRepository.save(usageRecord);

        return mapToResponse(savedUsageRecord);
    }
    private void validateUsageRecordRequest(UsageRecordRequest request){
        if(request.getUsageType() == UsageType.CALL){
            if(request.getCallMinutes() == null){
                throw new IllegalArgumentException("Call minutes are required for CALL usage.");
            }
        }
        if(request.getUsageType() == UsageType.SMS){
            if(request.getSmsCount() == null){
                throw new IllegalArgumentException("SMS count is required for SMS usage.");
            }
        }
        if(request.getUsageType() == UsageType.DATA){
            if(request.getDataUsedMb() == null){
                throw new IllegalArgumentException("Data used (MB) is required for DATA usage.");
            }
        }
    }

    @Override
    public List<UsageRecordResponse> getAllUsageRecords() {
        return usageRecordRepository.findAll()
                .stream()
                .map(this :: mapToResponse)
                .toList();
    }

    @Override
    public UsageRecordResponse getUsageRecordById(Long id) {
        UsageRecord usageRecord = usageRecordRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Usage record not found with id: "+id));
        return mapToResponse(usageRecord);
    }

    @Override
    public List<UsageRecordResponse> getUsageRecordsByMobileNumberId(Long mobileNumberId) {
        return usageRecordRepository.findByMobileNumber_Id(mobileNumberId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<UsageRecordResponse> getUsageRecordsBySubscriptionId(Long subscriptionId) {
        return usageRecordRepository.findBySubscription_Id(subscriptionId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    @Override
    public List<UsageRecordResponse> getUsageRecordsByUsageType(UsageType usageType) {
        return usageRecordRepository.findByUsageType(usageType)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    @Override
    public List<UsageRecordResponse> getUsageRecordsByUsageCategory(UsageCategory usageCategory) {
        return usageRecordRepository.findByUsageCategory(usageCategory)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    private UsageRecordResponse mapToResponse(UsageRecord usageRecord){
        return UsageRecordResponse.builder()
                .id(usageRecord.getId())
                .mobileNumberId(usageRecord.getMobileNumber().getId())
                .mobileNumber(usageRecord.getMobileNumber().getMobileNumber())
                .subscriptionId(usageRecord.getSubscription().getId())
                .usageType(usageRecord.getUsageType())
                .usageCategory(usageRecord.getUsageCategory())
                .applicationName(usageRecord.getApplicationName())
                .callMinutes(usageRecord.getCallMinutes())
                .smsCount(usageRecord.getSmsCount())
                .dataUsedMb(usageRecord.getDataUsedMb())
                .usageDateTime(usageRecord.getUsageDateTime())
                .build();
    }
}
