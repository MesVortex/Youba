package com.example.accountservice.mappers;

import com.example.accountservice.dto.AccountRequestDTO;
import com.example.accountservice.dto.AccountResponseDTO;
import com.example.accountservice.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account requestDTOToAccount(AccountRequestDTO requestDTO);
    AccountResponseDTO accountToResponseDTO(Account account);
}

