package com.telecom.tsms.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PlanRequest {

    @NotBlank(message = "Plan code is required")
    @Size(max = 30,message = "Plan code must not exceed 30 characters")
    private String planCode;

    @NotBlank(message = "Plan name is required")
    @Size(max = 100, message = "Plan name must not exceed 100 characters")
    private String planName;

    @NotBlank(message = "Plan type is required")
    @Size(max = 30,message = "Plan code must not exceed 30 characters")
    private String planType;

    @NotNull(message = "Validity days is required ")
    private Integer validityDays;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false,message = "price must be greater than 0")
    private BigDecimal price;

    private Double dataLimitGb;
    private Integer smsLimit;
    private Integer voiceLimitMinutes;
    @NotNull(message = "Active status is required")
    private Boolean active;

}
