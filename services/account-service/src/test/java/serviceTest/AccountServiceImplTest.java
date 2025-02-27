package serviceTest;

import com.example.accountservice.dto.AccountRequestDTO;
import com.example.accountservice.dto.AccountResponseDTO;
import com.example.accountservice.dto.CustomerDTO;
import com.example.accountservice.entities.Account;
import com.example.accountservice.entities.enums.AccountType;
import com.example.accountservice.exceptions.AccountNotFoundException;
import com.example.accountservice.exceptions.DuplicateAccountException;
import com.example.accountservice.mappers.AccountMapper;
import com.example.accountservice.repositories.AccountRepository;
import com.example.accountservice.services.implementations.AccountServiceImpl;
import com.example.accountservice.services.implementations.CustomerServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomerServiceClient customerServiceClient;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAccount_Success() {
        AccountRequestDTO requestDTO = new AccountRequestDTO();
        requestDTO.setCustomerId(1L);
        requestDTO.setType(AccountType.CHECKING);

        CustomerDTO customerDTO = new CustomerDTO();
        Account account = new Account();
        Account savedAccount = new Account();
        AccountResponseDTO responseDTO = new AccountResponseDTO();

        when(customerServiceClient.getCustomerById(requestDTO.getCustomerId())).thenReturn(customerDTO);
        when(accountRepository.existsByCustomerIdAndType(requestDTO.getCustomerId(), requestDTO.getType())).thenReturn(false);
        when(accountMapper.requestDTOToAccount(requestDTO)).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(savedAccount);
        when(accountMapper.accountToResponseDTO(savedAccount)).thenReturn(responseDTO);

        AccountResponseDTO result = accountService.createAccount(requestDTO);

        assertNotNull(result);
        verify(customerServiceClient).getCustomerById(requestDTO.getCustomerId());
        verify(accountRepository).existsByCustomerIdAndType(requestDTO.getCustomerId(), requestDTO.getType());
        verify(accountRepository).save(account);
    }

    @Test
    void testCreateAccount_DuplicateAccount() {
        AccountRequestDTO requestDTO = new AccountRequestDTO();
        requestDTO.setCustomerId(1L);
        requestDTO.setType(AccountType.CHECKING);

        when(customerServiceClient.getCustomerById(requestDTO.getCustomerId())).thenReturn(new CustomerDTO());
        when(accountRepository.existsByCustomerIdAndType(requestDTO.getCustomerId(), requestDTO.getType())).thenReturn(true);

        assertThrows(DuplicateAccountException.class, () -> accountService.createAccount(requestDTO));
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    void testGetAccountById_Success() {
        Long accountId = 1L;
        Account account = new Account();
        AccountResponseDTO responseDTO = new AccountResponseDTO();

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(accountMapper.accountToResponseDTO(account)).thenReturn(responseDTO);

        AccountResponseDTO result = accountService.getAccountById(accountId);

        assertNotNull(result);
        verify(accountRepository).findById(accountId);
    }

    @Test
    void testGetAccountById_NotFound() {
        Long accountId = 1L;

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> accountService.getAccountById(accountId));
        verify(accountRepository).findById(accountId);
    }

    @Test
    void testGetAccountsByCustomerId() {
        Long customerId = 1L;
        Account account = new Account();
        AccountResponseDTO responseDTO = new AccountResponseDTO();

        when(accountRepository.findByCustomerId(customerId)).thenReturn(Collections.singletonList(account));
        when(accountMapper.accountToResponseDTO(account)).thenReturn(responseDTO);

        List<AccountResponseDTO> result = accountService.getAccountsByCustomerId(customerId);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(accountRepository).findByCustomerId(customerId);
    }
}
