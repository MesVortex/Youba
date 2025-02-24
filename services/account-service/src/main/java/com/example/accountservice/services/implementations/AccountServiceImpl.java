package com.example.accountservice.services.implementations;

import com.example.accountservice.dto.AccountRequestDTO;
import com.example.accountservice.dto.AccountResponseDTO;
import com.example.accountservice.dto.AccountWithClientDTO;
import com.example.accountservice.dto.CustomerDTO;
import com.example.accountservice.entities.Account;
import com.example.accountservice.mappers.AccountMapper;
import com.example.accountservice.repositories.AccountRepository;
import com.example.accountservice.services.interfaces.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerServiceClient customerServiceClient;
    private final RestTemplate restTemplate;
    private final AccountMapper accountMapper = AccountMapper.INSTANCE;

    public AccountServiceImpl(AccountRepository accountRepository, CustomerServiceClient customerServiceClient, RestTemplate restTemplate) {
        this.accountRepository = accountRepository;
        this.customerServiceClient = customerServiceClient;
        this.restTemplate = restTemplate;
    }

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO requestDTO) {
        // Validate customer existence
        CustomerDTO customer = customerServiceClient.getCustomerById(requestDTO.getCustomerId());
        if (customer == null) {
            throw new RuntimeException("Customer not found with ID: " + requestDTO.getCustomerId());
        }

        // Check if the customer already has an account of the requested type
        boolean accountExists = accountRepository.existsByCustomerIdAndType(requestDTO.getCustomerId(), requestDTO.getType());
        if (accountExists) {
            throw new RuntimeException("Customer already has a " + requestDTO.getType() + " account.");
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

    @Override
    public List<AccountWithClientDTO> getAllAccountsWithClient() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(account -> {
                    AccountWithClientDTO accountWithClientDTO = new AccountWithClientDTO();
                    accountWithClientDTO.setId(account.getId());
                    accountWithClientDTO.setType(account.getType().name());
                    accountWithClientDTO.setBalance(account.getBalance());
                    accountWithClientDTO.setCustomerId(account.getCustomerId());

                    // Fetch client details
                    String url = "http://localhost:8080/customers/" + account.getCustomerId();
                    try {
                        ResponseEntity<CustomerDTO> response = restTemplate.exchange(
                                url,
                                HttpMethod.GET,
                                null,
                                CustomerDTO.class
                        );
                        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                            CustomerDTO customer = response.getBody();
                            accountWithClientDTO.setName(customer.getName());
                            accountWithClientDTO.setEmail(customer.getEmail());
                        }
                    } catch (HttpClientErrorException.NotFound e) {
                        log.warn("Client with ID {} not found.", account.getCustomerId());
                    } catch (Exception e) {
                        log.error("Error fetching client details for client ID {}", account.getCustomerId(), e);
                    }

                    return accountWithClientDTO;
                })
                .collect(Collectors.toList());
    }
}

