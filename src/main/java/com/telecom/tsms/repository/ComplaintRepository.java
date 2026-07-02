package com.telecom.tsms.repository;

import com.telecom.tsms.entity.Complaint;
import com.telecom.tsms.enums.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint,Long> {
    List<Complaint> findByMobileNumber_Id(Long mobileNumberId);
    List<Complaint> findByStatus(ComplaintStatus status);
    List<Complaint> findByMobileNumber_IdAndStatus(
            Long mobileNumberId,
            ComplaintStatus status
    );
}