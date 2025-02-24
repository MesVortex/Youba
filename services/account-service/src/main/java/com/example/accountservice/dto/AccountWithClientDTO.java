package com.example.accountservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountWithClientDTO {
    private Long id;
    private String type;
    private BigDecimal balance;
    private Long customerId;
    private String name;
    private String email;
}
