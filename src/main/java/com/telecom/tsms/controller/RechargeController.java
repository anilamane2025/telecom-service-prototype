package com.telecom.tsms.controller;

import com.telecom.tsms.dto.RechargeRequest;
import com.telecom.tsms.dto.RechargeResponse;
import com.telecom.tsms.service.RechargeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recharges")
public class RechargeController {

    private final RechargeService rechargeService;

    public RechargeController(RechargeService rechargeService){
        this.rechargeService = rechargeService;
    }

    //Process Recharge
    @PostMapping
    public ResponseEntity<RechargeResponse> processRecharge(
            @Valid @RequestBody RechargeRequest request){
        return ResponseEntity.ok(rechargeService.processRecharge(request));
    }

    //Get All Recharges
    @GetMapping
    public ResponseEntity<List<RechargeResponse>> getAllRecharges(){
        return ResponseEntity.ok(rechargeService.getAllRechargeTransactions());
    }

    //Get Recharge By ID
    @GetMapping("/{id}")
    public ResponseEntity<RechargeResponse> getRechargeById(@PathVariable Long id){
        return ResponseEntity.ok(rechargeService.getRechargeTransactionById(id));
    }

    //Get Recharge History By Customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<RechargeResponse>> getRechargeByCustomerId(
        @PathVariable Long customerId){
        return ResponseEntity.ok(
                rechargeService.getRechargeTransactionByCustomerId(customerId)
        );
    }
}
