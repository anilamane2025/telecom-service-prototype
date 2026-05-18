package com.telecom.tsms.controller;

import com.telecom.tsms.dto.PlanRequest;
import com.telecom.tsms.dto.PlanResponse;
import com.telecom.tsms.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Plan APIs",description = "Operations related to plans")
@RestController
@RequestMapping("/api/plans")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService){
        this.planService = planService;
    }

    @Operation(summary = "Create plan")
    @PostMapping
    public ResponseEntity<PlanResponse> createPlan(@Valid @RequestBody PlanRequest planRequest){
        PlanResponse createdPlan = planService.createPlan(planRequest);
        return new ResponseEntity<>(createdPlan, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all plans")
    @GetMapping
    public ResponseEntity<List<PlanResponse>> getAllPlans(){
        List<PlanResponse> plans = planService.getAllPlans();
        return ResponseEntity.ok(plans);
    }

    @Operation(summary = "Get plan by ID")
    @GetMapping("/{id}")
    public ResponseEntity<PlanResponse> getPlanById(@PathVariable Long id){
        PlanResponse plan = planService.getPlanById(id);
        return ResponseEntity.ok(plan);
    }
    @Operation(summary = "Get plan by Type")
    @GetMapping("/type/{planType}")
    public ResponseEntity<List<PlanResponse>> getPlansByType(@PathVariable String planType){
        List<PlanResponse> plans = planService.getPlansByType(planType);
        return ResponseEntity.ok(plans);
    }

    @Operation(summary = "Update plan by ID")
    @PutMapping("/{id}")
    public ResponseEntity<PlanResponse> updatePlan(@PathVariable Long id, @Valid @RequestBody PlanRequest planRequest){
        PlanResponse updatedPlan = planService.updatePlan(id,planRequest);
        return ResponseEntity.ok(updatedPlan);
    }

    @Operation(summary = "Delete plan by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlan(@PathVariable Long id){
        planService.deletePlan(id);
        return ResponseEntity.ok("Plan deleted successfully with id "+id);
    }
}