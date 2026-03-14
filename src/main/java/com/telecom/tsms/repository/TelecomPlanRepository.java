package com.telecom.tsms.repository;

import com.telecom.tsms.entity.TelecomPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TelecomPlanRepository extends JpaRepository<TelecomPlan,Long> {

        Optional<TelecomPlan> findByPlanCode(String planCode);

        List<TelecomPlan> findByPlanType(String planType);
        List<TelecomPlan> findByActiveTrue();

}
