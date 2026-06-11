package com.telecom.tsms.controller;

import com.telecom.tsms.dto.MobileNumberRequest;
import com.telecom.tsms.dto.MobileNumberResponse;
import com.telecom.tsms.entity.MobileNumber;
import com.telecom.tsms.service.MobileNumberService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mobile-numbers")
public class MobileNumberController {

    private final MobileNumberService mobileNumberService;

    public MobileNumberController(MobileNumberService mobileNumberService){
        this.mobileNumberService = mobileNumberService;
    }

    @Operation(summary = "Add MobileNumbers")
    @PostMapping
    public ResponseEntity<MobileNumberResponse> createMobileNumber(@Valid @RequestBody MobileNumberRequest request){
        MobileNumberResponse response = mobileNumberService.createMobileNumber(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get All Mobile Numbers")
    @GetMapping
    public ResponseEntity<List<MobileNumberResponse>> getAllMobileNumbers(){
        List<MobileNumberResponse> response = mobileNumberService.getAllMobileNumbers();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get Mobile Number By Id")
    @GetMapping("/{id}")
    public ResponseEntity<MobileNumberResponse> getMobileNumberById(@PathVariable Long id){
        MobileNumberResponse response = mobileNumberService.getMobileNumberById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update Mobile Number By Id")
    @PutMapping("/{id}")
    public ResponseEntity<MobileNumberResponse> updateMobileNumber(@PathVariable Long id,@RequestBody MobileNumberRequest request){
        MobileNumberResponse response = mobileNumberService.updateMobileNumber(id,request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete Mobile Number By Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMobileNumber(@PathVariable Long id){
        mobileNumberService.deleteMobileNumber(id);
        return ResponseEntity.ok("Mobile Number deleted successfully with id: "+id);
    }
}
