package com.telecom.tsms.service;

import com.telecom.tsms.dto.ComplaintRequest;
import com.telecom.tsms.dto.ComplaintResolutionRequest;
import com.telecom.tsms.dto.ComplaintResponse;
import com.telecom.tsms.entity.Complaint;
import com.telecom.tsms.entity.MobileNumber;
import com.telecom.tsms.enums.ComplaintStatus;
import com.telecom.tsms.enums.ComplaintType;
import com.telecom.tsms.exception.ResourceNotFoundException;
import com.telecom.tsms.repository.ComplaintRepository;
import com.telecom.tsms.repository.MobileNumberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComplaintServiceImpl implements ComplaintService{


    private final ComplaintRepository complaintRepository;
    private final MobileNumberRepository mobileNumberRepository;

    private final NotificationService notificationService;
    public ComplaintServiceImpl(ComplaintRepository complaintRepository,
                                MobileNumberRepository mobileNumberRepository,
                                NotificationService notificationService){
        this.complaintRepository = complaintRepository;
        this.mobileNumberRepository = mobileNumberRepository;
        this.notificationService = notificationService;
    }

    @Override
    public ComplaintResponse createComplaint(ComplaintRequest request) {


            MobileNumber mobileNumber = mobileNumberRepository.findById(request.getMobileNumberId())
                    .orElseThrow(()->new ResourceNotFoundException("Mobile number not found with id: "+request.getMobileNumberId()));

            Complaint complaint = Complaint.builder()
                    .mobileNumber(mobileNumber)
                    .complaintType(request.getComplaintType())
                    .description(request.getDescription())
                    .status(ComplaintStatus.OPEN)
                    .createdAt(LocalDateTime.now())
                    .build();

            Complaint saved = complaintRepository.save(complaint);
            notificationService.sendComplaintCreatedNotification(saved);
            return mapToResponse(saved);
    }

    @Override
    public List<ComplaintResponse> getAllComplaints() {
        return complaintRepository.findAll()
                .stream()
                .map(this :: mapToResponse)
                .toList();
    }

    @Override
    public ComplaintResponse getComplaintById(Long id) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Complaint not found with id: " + id));
        return  mapToResponse(complaint);
    }

    @Override
    public List<ComplaintResponse> getComplaintsByMobileNumberId(Long mobileNumberId) {

         return complaintRepository.findByMobileNumber_Id(mobileNumberId)
                .stream()
                .map(this::mapToResponse)
                 .toList();

    }
    @Override
    public List<ComplaintResponse> getComplaintsByStatus(ComplaintStatus status) {

        return complaintRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ComplaintResponse resolveComplaint(Long id, ComplaintResolutionRequest request) {

        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Complaint not found with id: "+id));

        if(complaint.getStatus() == ComplaintStatus.RESOLVED ||
           complaint.getStatus() == ComplaintStatus.CLOSED){
            throw new RuntimeException("Complaint already resolved or closed");
        }
        complaint.setStatus(ComplaintStatus.RESOLVED);
        complaint.setResolutionRemarks(request.getResolutionRemarks());
        complaint.setResolvedAt(LocalDateTime.now());

        Complaint updated = complaintRepository.save(complaint);
        notificationService.sendComplaintResolvedNotification(updated);
        return mapToResponse(updated);
    }

    private ComplaintResponse mapToResponse(Complaint comp){

        return ComplaintResponse.builder()
                .id(comp.getId())
                .mobileNumberId(comp.getMobileNumber().getId())
                .mobileNumber(comp.getMobileNumber().getMobileNumber())
                .complaintType(comp.getComplaintType())
                .description(comp.getDescription())
                .status(comp.getStatus())
                .createdAt(comp.getCreatedAt())
                .resolved(comp.getStatus() == ComplaintStatus.RESOLVED
                            ||
                            comp.getStatus() == ComplaintStatus.CLOSED
                )
                .resolvedAt(comp.getResolvedAt())
                .resolutionRemarks(comp.getResolutionRemarks())
                .build();

    }



}
