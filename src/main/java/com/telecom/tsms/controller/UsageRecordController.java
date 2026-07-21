package com.telecom.tsms.controller;

import com.telecom.tsms.dto.UsageRecordRequest;
import com.telecom.tsms.dto.UsageRecordResponse;
import com.telecom.tsms.entity.UsageRecord;
import com.telecom.tsms.enums.UsageCategory;
import com.telecom.tsms.enums.UsageType;
import com.telecom.tsms.service.UsageRecordService;
import com.telecom.tsms.service.UsageRecordServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usage Record APIs",description = "Operations related to usage-record")
@RestController
@RequestMapping("/api/usage-record")
public class UsageRecordController {

    private final UsageRecordService usageRecordService;

    public UsageRecordController(UsageRecordService usageRecordService){
        this.usageRecordService = usageRecordService;
    }

    @Operation(summary = "Create usage record")
    @PostMapping
    public ResponseEntity<UsageRecordResponse> createUsageRecord(@Valid @RequestBody UsageRecordRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(usageRecordService.createUsageRecord(request));
    }

    @Operation(summary = "Get all usage records")
    @GetMapping
    public ResponseEntity<List<UsageRecordResponse>> getAllUsageRecords(){
        return ResponseEntity.ok(usageRecordService.getAllUsageRecords());
    }

    @Operation(summary = "Get usage record by ID")
    @GetMapping("/{id}")
    public ResponseEntity<UsageRecordResponse> getUsageRecordById(@PathVariable Long id){
        return ResponseEntity.ok(usageRecordService.getUsageRecordById(id));
    }

    @Operation(summary = "Get usage record by mobileNumberId")
    @GetMapping("/mobile-number/{mobileNumberId}")
    public ResponseEntity<List<UsageRecordResponse>> getUsageRecordsByMobileNumberId(@PathVariable Long mobileNumberId){
        return ResponseEntity.ok(usageRecordService.getUsageRecordsByMobileNumberId(mobileNumberId));
    }

    @Operation(summary = "Get usage record by subscriptionId")
    @GetMapping("/subscription/{subscriptionId}")
    public ResponseEntity<List<UsageRecordResponse>> getUsageRecordsBySubscriptionId(@PathVariable Long subscriptionId){
        return ResponseEntity.ok(usageRecordService.getUsageRecordsBySubscriptionId(subscriptionId));
    }

    @Operation(summary = "Get usage record by usageType")
    @GetMapping("/usage-type/{usageType}")
    public ResponseEntity<List<UsageRecordResponse>> getUsageRecordsByUsageType(@PathVariable UsageType usageType){
        return ResponseEntity.ok(usageRecordService.getUsageRecordsByUsageType(usageType));
    }

    @Operation(summary = "Get usage record by usageCategory")
    @GetMapping("/usage-category/{usageCategory}")
    public ResponseEntity<List<UsageRecordResponse>> getUsageRecordsByUsageCategory(@PathVariable UsageCategory usageCategory){
        return ResponseEntity.ok(usageRecordService.getUsageRecordsByUsageCategory(usageCategory));
    }
}
