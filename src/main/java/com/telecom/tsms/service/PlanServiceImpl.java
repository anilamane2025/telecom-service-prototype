package com.telecom.tsms.service;

import com.telecom.tsms.dto.PlanRequest;
import com.telecom.tsms.dto.PlanResponse;
import com.telecom.tsms.entity.TelecomPlan;
import com.telecom.tsms.exception.ResourceNotFoundException;
import com.telecom.tsms.repository.TelecomPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanServiceImpl implements PlanService{

    private final TelecomPlanRepository telecomPlanRepository;

    public PlanServiceImpl(TelecomPlanRepository telecomPlanRepository){
        this.telecomPlanRepository = telecomPlanRepository;
    }
    @Override
    public PlanResponse createPlan(PlanRequest planRequest) {
        TelecomPlan telecomPlan = mapToEntity(planRequest);
        TelecomPlan savedPlan = telecomPlanRepository.save(telecomPlan);
        return mapToResponse(savedPlan);
    }

    @Override
    public List<PlanResponse> getAllPlans() {
        List<TelecomPlan> telecomPlans = telecomPlanRepository.findAll();
        return telecomPlans.stream()
                            .map(this :: mapToResponse)
                            .toList();
    }

    @Override
    public PlanResponse getPlanById(Long id) {
        TelecomPlan telecomPlan = telecomPlanRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Plan not found with id: "+id));
        return mapToResponse(telecomPlan);
    }

    @Override
    public List<PlanResponse> getPlansByType(String planType) {
        List<TelecomPlan> telecomPlans = telecomPlanRepository.findByPlanType(planType);
        return telecomPlans.stream()
                .map(this :: mapToResponse)
                .toList();
    }

    @Override
    public PlanResponse updatePlan(Long id, PlanRequest planRequest) {
        TelecomPlan existingPlan = telecomPlanRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Plan not found with id: "+id));

        existingPlan.setPlanCode(planRequest.getPlanCode());
        existingPlan.setPlanName(planRequest.getPlanName());
        existingPlan.setPlanType(planRequest.getPlanType());
        existingPlan.setValidityDays(planRequest.getValidityDays());
        existingPlan.setPrice(planRequest.getPrice());
        existingPlan.setDataLimitGb(planRequest.getDataLimitGb());
        existingPlan.setSmsLimit(planRequest.getSmsLimit());
        existingPlan.setVoiceLimitMinutes(planRequest.getVoiceLimitMinutes());
        existingPlan.setActive(planRequest.getActive());

        TelecomPlan updatedPlan = telecomPlanRepository.save(existingPlan);

        return mapToResponse(updatedPlan);
    }

    @Override
    public void deletePlan(Long id) {
        TelecomPlan existingPlan = telecomPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found with id: "+id));
        telecomPlanRepository.delete(existingPlan);
    }

    private TelecomPlan mapToEntity(PlanRequest planRequest){
        return TelecomPlan.builder()
                .planCode(planRequest.getPlanCode())
                .planName(planRequest.getPlanName())
                .planType(planRequest.getPlanType())
                .validityDays(planRequest.getValidityDays())
                .price(planRequest.getPrice())
                .dataLimitGb(planRequest.getDataLimitGb())
                .smsLimit(planRequest.getSmsLimit())
                .voiceLimitMinutes(planRequest.getVoiceLimitMinutes())
                .active(planRequest.getActive())
                .build();
    }

    private PlanResponse mapToResponse(TelecomPlan telecomPlan){
        return PlanResponse.builder()
                .id(telecomPlan.getId())
                .planCode(telecomPlan.getPlanCode())
                .planName(telecomPlan.getPlanName())
                .planType(telecomPlan.getPlanType())
                .validityDays(telecomPlan.getValidityDays())
                .price(telecomPlan.getPrice())
                .dataLimitGb(telecomPlan.getDataLimitGb())
                .smsLimit(telecomPlan.getSmsLimit())
                .voiceLimitMinutes(telecomPlan.getVoiceLimitMinutes())
                .active(telecomPlan.getActive())
                .build();
    }

}