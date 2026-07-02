package com.telecom.tsms.entity;

import com.telecom.tsms.enums.ComplaintStatus;
import com.telecom.tsms.enums.ComplaintType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="mobile_number_id",nullable = false)
    private MobileNumber mobileNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 10)
    private ComplaintType complaintType;

    @Column(nullable = false,length = 500)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime resolvedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private ComplaintStatus status;

    @Column(length = 500)
    private String resolutionRemarks;
}