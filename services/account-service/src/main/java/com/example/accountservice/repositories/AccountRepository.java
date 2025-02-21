package com.example.accountservice.repositories;

import com.example.accountservice.entities.Account;
import com.example.accountservice.entities.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByCustomerId(Long customerId);
    boolean existsByCustomerIdAndType(Long customerId, AccountType type);
}

