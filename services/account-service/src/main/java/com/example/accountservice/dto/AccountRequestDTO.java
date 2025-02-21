package com.example.accountservice.dto;

import com.example.accountservice.entities.enums.AccountType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequestDTO {
    private BigDecimal balance;
    private AccountType type;
    private Long customerId;
}

