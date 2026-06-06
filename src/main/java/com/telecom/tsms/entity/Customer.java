package com.telecom.tsms.entity;

import com.telecom.tsms.enums.KycStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "customer")
    private List<MobileNumber> mobileNumbers;

    @Column(name = "customer_code",nullable = false,unique = true,length = 30)
    private String customerCode;

    @Column(name = "full_name",nullable = false,length = 100)
    private String fullName;

    @Column(nullable = false,length = 100)
    private String email;

    @Column(length = 50)
    private String state;

    @Enumerated(EnumType.STRING)
    @Column(name= "kyc_status",length = 30)
    private KycStatus kycStatus;

}