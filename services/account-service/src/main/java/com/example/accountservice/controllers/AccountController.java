package com.example.accountservice.controllers;

import com.example.accountservice.dto.AccountRequestDTO;
import com.example.accountservice.dto.AccountResponseDTO;
import com.example.accountservice.services.interfaces.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountRequestDTO requestDTO) {
        return ResponseEntity.ok(accountService.createAccount(requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<AccountResponseDTO>> getAccountsByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(accountService.getAccountsByCustomerId(customerId));
    }
}

