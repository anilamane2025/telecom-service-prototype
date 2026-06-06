package com.telecom.tsms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="mobile_number")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MobileNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="mobile_number",nullable = false,unique = true,length = 15)
    private String mobileNumber;

    @Column(name="is_primary")
    private boolean isPrimary;

    @Column(length=30)
    private String status;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;


}
