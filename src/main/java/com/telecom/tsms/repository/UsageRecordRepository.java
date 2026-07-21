package com.telecom.tsms.repository;

import com.telecom.tsms.entity.Customer;
import com.telecom.tsms.entity.UsageRecord;
import com.telecom.tsms.enums.UsageCategory;
import com.telecom.tsms.enums.UsageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsageRecordRepository extends JpaRepository<UsageRecord,Long> {

    //Optional<UsageRecord> findByCustomer(Customer customer);

    List<UsageRecord> findByMobileNumber_Id(Long mobileNumberId);
    List<UsageRecord> findBySubscription_Id(Long subscriptionId);
    List<UsageRecord> findByUsageType(UsageType usageType);
    List<UsageRecord> findByUsageCategory(UsageCategory usageCategory);

}
