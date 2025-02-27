package serviceTest;

import com.example.accountservice.dto.CustomerDTO;
import com.example.accountservice.exceptions.CustomerNotFoundException;
import com.example.accountservice.services.implementations.CustomerServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CustomerServiceClient customerServiceClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCustomerById_Success() {
        Long customerId = 1L;
        CustomerDTO customerDTO = new CustomerDTO();

        when(restTemplate.getForObject(anyString(), eq(CustomerDTO.class))).thenReturn(customerDTO);

        CustomerDTO result = customerServiceClient.getCustomerById(customerId);

        assertNotNull(result);
        verify(restTemplate).getForObject(anyString(), eq(CustomerDTO.class));
    }

    @Test
    void testGetCustomerById_NotFound() {
        Long customerId = 1L;

        when(restTemplate.getForObject(anyString(), eq(CustomerDTO.class))).thenThrow(HttpClientErrorException.NotFound.class);

        assertThrows(CustomerNotFoundException.class, () -> customerServiceClient.getCustomerById(customerId));
        verify(restTemplate).getForObject(anyString(), eq(CustomerDTO.class));
    }
}
