package com.example.accountservice.services.implementations;

import com.example.accountservice.dto.CustomerDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerServiceClient {

    private final RestTemplate restTemplate;

    private static final String CUSTOMER_SERVICE_URL = "http://localhost:8080/customers";

    public CustomerServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CustomerDTO getCustomerById(Long customerId) {
        String url = CUSTOMER_SERVICE_URL + "/" + customerId;
        return restTemplate.getForObject(url, CustomerDTO.class);
    }
}

