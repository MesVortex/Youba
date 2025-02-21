package com.example.accountservice.services.interfaces;

import com.example.accountservice.dto.AccountRequestDTO;
import com.example.accountservice.dto.AccountResponseDTO;
import java.util.List;

public interface AccountService {
    AccountResponseDTO createAccount(AccountRequestDTO requestDTO);
    AccountResponseDTO getAccountById(Long id);
    List<AccountResponseDTO> getAccountsByCustomerId(Long customerId);
}

