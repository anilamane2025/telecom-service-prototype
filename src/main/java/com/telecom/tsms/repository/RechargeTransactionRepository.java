package com.telecom.tsms.repository;

import com.telecom.tsms.entity.Customer;
import com.telecom.tsms.entity.RechargeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RechargeTransactionRepository extends JpaRepository<RechargeTransaction,Long> {

    List<RechargeTransaction> findByCustomer(Customer customer);

}
