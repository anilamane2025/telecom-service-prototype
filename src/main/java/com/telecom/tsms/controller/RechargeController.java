package com.telecom.tsms.controller;

import com.telecom.tsms.dto.RechargeRequest;
import com.telecom.tsms.dto.RechargeResponse;
import com.telecom.tsms.service.RechargeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Recharge APIs",description = "Operations related to recharge")
@RestController
@RequestMapping("/api/recharges")
public class RechargeController {

    private final RechargeService rechargeService;

    public RechargeController(RechargeService rechargeService){
        this.rechargeService = rechargeService;
    }

    //Process Recharge
    @Operation(summary = "Process recharge")
    @PostMapping
    public ResponseEntity<RechargeResponse> processRecharge(
            @Valid @RequestBody RechargeRequest request){
        return ResponseEntity.ok(rechargeService.processRecharge(request));
    }

    //Get All Recharges
    @Operation(summary = "Get all recharge")
    @GetMapping
    public ResponseEntity<List<RechargeResponse>> getAllRecharges(){
        return ResponseEntity.ok(rechargeService.getAllRechargeTransactions());
    }

    //Get Recharge By ID
    @Operation(summary = "Get recharge by ID")
    @GetMapping("/{id}")
    public ResponseEntity<RechargeResponse> getRechargeById(@PathVariable Long id){
        return ResponseEntity.ok(rechargeService.getRechargeTransactionById(id));
    }

    //Get Recharge History By Customer
    /*@Operation(summary = "Get recharge by Customer ID")
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<RechargeResponse>> getRechargeByCustomerId(
        @PathVariable Long customerId){
        return ResponseEntity.ok(
                rechargeService.getRechargeTransactionByCustomerId(customerId)
        );
    }*/

    /*@GetMapping("/my")
    public ResponseEntity<List<RechargeResponse>> getMyRechargeHistory(){
        return ResponseEntity.ok(
                rechargeService.getMyRechargeHistory()
        );
    }*/
}
