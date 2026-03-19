package com.telecom.tsms.service;

import com.telecom.tsms.dto.PlanRequest;
import com.telecom.tsms.dto.PlanResponse;

import java.util.List;

public interface PlanService {
    PlanResponse createPlan(PlanRequest planRequest);
    List<PlanResponse> getAllPlans();
    PlanResponse getPlanById(Long id);
    List<PlanResponse> getPlansByType(String planType);
    PlanResponse updatePlan(Long id,PlanRequest planRequest);
    void deletePlan(Long id);
}
