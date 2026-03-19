package com.telecom.tsms.controller;

import com.telecom.tsms.dto.PlanRequest;
import com.telecom.tsms.dto.PlanResponse;
import com.telecom.tsms.service.PlanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService){
        this.planService = planService;
    }

    @PostMapping
    public ResponseEntity<PlanResponse> createPlan(@Valid @RequestBody PlanRequest planRequest){
        PlanResponse createdPlan = planService.createPlan(planRequest);
        return new ResponseEntity<>(createdPlan, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PlanResponse>> getAllPlans(){
        List<PlanResponse> plans = planService.getAllPlans();
        return ResponseEntity.ok(plans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanResponse> getPlanById(@PathVariable Long id){
        PlanResponse plan = planService.getPlanById(id);
        return ResponseEntity.ok(plan);
    }

    @GetMapping("/type/{planType}")
    public ResponseEntity<List<PlanResponse>> getPlansByType(@PathVariable String type){
        List<PlanResponse> plans = planService.getPlansByType(type);
        return ResponseEntity.ok(plans);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanResponse> updatePlan(@PathVariable Long id, @Valid @RequestBody PlanRequest planRequest){
        PlanResponse updatedPlan = planService.updatePlan(id,planRequest);
        return ResponseEntity.ok(updatedPlan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlan(@PathVariable Long id){
        planService.deletePlan(id);
        return ResponseEntity.ok("Plan deleted successfully with id "+id);
    }
}