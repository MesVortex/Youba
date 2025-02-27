package serviceTest;

import com.example.customerservice.dto.CustomerRequestDTO;
import com.example.customerservice.dto.CustomerResponseDTO;
import com.example.customerservice.entity.Customer;
import com.example.customerservice.exception.CustomerNotFoundException;
import com.example.customerservice.exception.DuplicateEmailException;
import com.example.customerservice.mapper.CustomerMapper;
import com.example.customerservice.repository.CustomerRepository;
import com.example.customerservice.service.implementation.CustomerServiceImpl;
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

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCustomer_Success() {
        CustomerRequestDTO requestDTO = new CustomerRequestDTO();
        requestDTO.setEmail("test@example.com");

        Customer customer = new Customer();
        Customer savedCustomer = new Customer();
        CustomerResponseDTO responseDTO = new CustomerResponseDTO();

        when(customerRepository.findByEmail(requestDTO.getEmail())).thenReturn(Optional.empty());
        when(customerMapper.toEntity(requestDTO)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(savedCustomer);
        when(customerMapper.toResponseDTO(savedCustomer)).thenReturn(responseDTO);

        CustomerResponseDTO result = customerService.addCustomer(requestDTO);

        assertNotNull(result);
        verify(customerRepository).findByEmail(requestDTO.getEmail());
        verify(customerRepository).save(customer);
    }

    @Test
    void testAddCustomer_DuplicateEmail() {
        CustomerRequestDTO requestDTO = new CustomerRequestDTO();
        requestDTO.setEmail("test@example.com");

        Customer existingCustomer = new Customer();

        when(customerRepository.findByEmail(requestDTO.getEmail())).thenReturn(Optional.of(existingCustomer));

        assertThrows(DuplicateEmailException.class, () -> customerService.addCustomer(requestDTO));
        verify(customerRepository).findByEmail(requestDTO.getEmail());
        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    void testGetCustomerById_Success() {
        Long customerId = 1L;
        Customer customer = new Customer();
        CustomerResponseDTO responseDTO = new CustomerResponseDTO();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerMapper.toResponseDTO(customer)).thenReturn(responseDTO);

        CustomerResponseDTO result = customerService.getCustomerById(customerId);

        assertNotNull(result);
        verify(customerRepository).findById(customerId);
    }

    @Test
    void testGetCustomerById_NotFound() {
        Long customerId = 1L;

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(customerId));
        verify(customerRepository).findById(customerId);
    }

    @Test
    void testGetAllCustomers() {
        Customer customer = new Customer();
        CustomerResponseDTO responseDTO = new CustomerResponseDTO();

        when(customerRepository.findAll()).thenReturn(Collections.singletonList(customer));
        when(customerMapper.toResponseDTO(customer)).thenReturn(responseDTO);

        List<CustomerResponseDTO> result = customerService.getAllCustomers();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(customerRepository).findAll();
    }
}
