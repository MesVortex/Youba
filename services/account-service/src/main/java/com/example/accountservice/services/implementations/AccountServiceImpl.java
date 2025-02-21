package com.example.accountservice.services.implementations;

import com.example.accountservice.dto.AccountRequestDTO;
import com.example.accountservice.dto.AccountResponseDTO;
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
    private final AccountMapper accountMapper = AccountMapper.INSTANCE;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO requestDTO) {
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

