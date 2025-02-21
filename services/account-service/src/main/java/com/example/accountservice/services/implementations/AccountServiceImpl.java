package com.example.accountservice.services.implementations;

import com.example.accountservice.dto.AccountRequestDTO;
import com.example.accountservice.dto.AccountResponseDTO;
import com.example.accountservice.dto.CustomerDTO;
import com.example.accountservice.entities.Account;
import com.example.accountservice.mappers.AccountMapper;
import com.example.accountservice.repositories.AccountRepository;
import com.example.accountservice.services.interfaces.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerServiceClient customerServiceClient;
    private final AccountMapper accountMapper = AccountMapper.INSTANCE;

    public AccountServiceImpl(AccountRepository accountRepository, CustomerServiceClient customerServiceClient) {
        this.accountRepository = accountRepository;
        this.customerServiceClient = customerServiceClient;
    }

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO requestDTO) {
        // Validate customer existence
        CustomerDTO customer = customerServiceClient.getCustomerById(requestDTO.getCustomerId());
        if (customer == null) {
            throw new RuntimeException("Customer not found with ID: " + requestDTO.getCustomerId());
        }

        Account account = accountMapper.requestDTOToAccount(requestDTO);
        Account savedAccount = accountRepository.save(account);
        return accountMapper.accountToResponseDTO(savedAccount);
    }


    @Override
    public AccountResponseDTO getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return accountMapper.accountToResponseDTO(account);
    }

    @Override
    public List<AccountResponseDTO> getAccountsByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId).stream()
                .map(accountMapper::accountToResponseDTO)
                .collect(Collectors.toList());
    }
}

