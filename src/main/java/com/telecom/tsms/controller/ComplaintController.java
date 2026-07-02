package com.telecom.tsms.controller;

import com.telecom.tsms.dto.ComplaintRequest;
import com.telecom.tsms.dto.ComplaintResolutionRequest;
import com.telecom.tsms.dto.ComplaintResponse;
import com.telecom.tsms.enums.ComplaintStatus;
import com.telecom.tsms.service.ComplaintService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Complaint API",description = "Operation related to complaint")
@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {
    private final ComplaintService complaintService;
    public ComplaintController(ComplaintService complaintService){
        this.complaintService = complaintService;
    }
    @Operation(summary = "Create Complaint")
    @PostMapping()
    ResponseEntity<ComplaintResponse> createComplaint(@Valid @RequestBody ComplaintRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(complaintService.createComplaint(request));
    }

    @Operation(summary = "Get all complaints")
    @GetMapping()
    public ResponseEntity<List<ComplaintResponse>> getAllComplaints(){
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

    @Operation(summary = "Get complaint by id")
    @GetMapping("/{id}")
    public ResponseEntity<ComplaintResponse> getComplaintById(@PathVariable Long id){
        return ResponseEntity.ok(complaintService.getComplaintById(id));
    }

    @Operation(summary = "Get complaint by mobile_number_id")
    @GetMapping("/mobile/{mobileNumberId}")
    ResponseEntity<List<ComplaintResponse>> getComplaintsByMobileNumberId(@PathVariable Long mobileNumberId){
        return ResponseEntity.ok(complaintService.getComplaintsByMobileNumberId(mobileNumberId));
    }

    @Operation(summary = "Get complaint by status")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ComplaintResponse>> getComplaintsByStatus(@PathVariable ComplaintStatus status){
        return ResponseEntity.ok(complaintService.getComplaintsByStatus(status));
    }

    @Operation(summary = "Resolve the complaint")
    @PutMapping("/{id}/resolve")
    public ResponseEntity<ComplaintResponse> resolveComplaint(@PathVariable Long id, @Valid @RequestBody ComplaintResolutionRequest request){
        return ResponseEntity.ok(complaintService.resolveComplaint(id,request));
    }
}
