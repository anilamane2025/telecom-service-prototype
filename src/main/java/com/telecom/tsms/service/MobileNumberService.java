package com.telecom.tsms.service;

import com.telecom.tsms.dto.MobileNumberRequest;
import com.telecom.tsms.dto.MobileNumberResponse;
import com.telecom.tsms.entity.MobileNumber;

import java.util.List;

public interface MobileNumberService {

    MobileNumberResponse createMobileNumber(MobileNumberRequest request);
    List<MobileNumberResponse> getAllMobileNumbers();

    MobileNumberResponse getMobileNumberById(Long id);

    MobileNumberResponse updateMobileNumber(Long id,MobileNumberRequest request);

    void deleteMobileNumber(Long id);

}
