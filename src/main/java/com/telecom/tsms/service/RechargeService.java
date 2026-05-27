package com.telecom.tsms.service;

import com.telecom.tsms.dto.RechargeRequest;
import com.telecom.tsms.dto.RechargeResponse;

import java.util.List;

public interface RechargeService {

    RechargeResponse processRecharge(RechargeRequest request);
    List<RechargeResponse> getAllRechargeTransactions();
    RechargeResponse getRechargeTransactionById(Long id);
    List<RechargeResponse> getRechargeTransactionByCustomerId(Long id);

    List<RechargeResponse> getMyRechargeHistory();
}
