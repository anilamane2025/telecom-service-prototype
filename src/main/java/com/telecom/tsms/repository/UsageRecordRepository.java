package com.telecom.tsms.repository;

import com.telecom.tsms.entity.Customer;
import com.telecom.tsms.entity.UsageRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsageRecordRepository extends JpaRepository<UsageRecord,Long> {

    Optional<UsageRecord> findByCustomer(Customer customer);

}
