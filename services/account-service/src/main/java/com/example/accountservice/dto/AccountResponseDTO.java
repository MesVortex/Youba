package com.example.accountservice.dto;

import com.example.accountservice.entities.enums.AccountType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Data
public class AccountResponseDTO {
    private Long id;
    private BigDecimal balance;
    private AccountType type;
    private Long customerId;
}

