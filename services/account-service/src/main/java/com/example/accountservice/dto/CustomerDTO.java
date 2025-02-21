package com.example.accountservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Data
@Setter
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
}

