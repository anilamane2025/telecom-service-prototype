package com.telecom.tsms.repository;

import com.telecom.tsms.entity.Customer;
import com.telecom.tsms.entity.Subscription;
import jdk.jshell.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {

    List<Subscription> findByCustomer(Customer customor);
    Optional<Subscription> findByCustomerAndStatus(Customer customer, String status);

    List<Subscription> findByCustomerId(Long customerId);
}
