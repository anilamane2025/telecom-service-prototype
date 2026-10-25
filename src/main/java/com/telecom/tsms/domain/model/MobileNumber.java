package com.telecom.tsms.domain.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MobileNumber {

    private Long id;
    private String mobileNumber;
    private boolean isPrimary;
    private String status;
    private Long customerId;

}
