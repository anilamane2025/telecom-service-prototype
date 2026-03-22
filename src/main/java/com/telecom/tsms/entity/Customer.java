package com.telecom.tsms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_code",nullable = false,unique = true,length = 30)
    private String customerCode;

    @Column(name = "full_name",nullable = false,length = 100)
    private String fullName;

    @Column(name = "mobile_number", nullable = false,unique = true, length = 15)
    private String mobileNumber;

    @Column(nullable = false,length = 100)
    private String email;

    @Column(length = 50)
    private String state;

    @Column(name= "kyc_status",length = 30)
    private String kycStatus;

}
