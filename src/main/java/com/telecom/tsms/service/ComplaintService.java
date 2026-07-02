package com.telecom.tsms.service;

import com.telecom.tsms.dto.ComplaintRequest;
import com.telecom.tsms.dto.ComplaintResolutionRequest;
import com.telecom.tsms.dto.ComplaintResponse;
import com.telecom.tsms.enums.ComplaintStatus;

import java.util.List;

public interface ComplaintService {

    ComplaintResponse createComplaint(ComplaintRequest request);
    List<ComplaintResponse> getAllComplaints();
    ComplaintResponse getComplaintById(Long id);
    List<ComplaintResponse> getComplaintsByMobileNumberId(Long mobileNumberId);
    List<ComplaintResponse> getComplaintsByStatus(ComplaintStatus status);
    ComplaintResponse resolveComplaint(Long id, ComplaintResolutionRequest request);

}