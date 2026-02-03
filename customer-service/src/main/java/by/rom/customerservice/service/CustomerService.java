package by.rom.customerservice.service;

import by.rom.customerservice.dto.CustomerDto;
import by.rom.customerservice.model.Customer;
import by.rom.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer createCustomer(CustomerDto customerDto) {
        return customerRepository.save(Customer.builder()
                .email(customerDto.getEmail())
                .name(customerDto.getName())
                .phoneNumber(customerDto.getPhoneNumber())
                .build());
    }

    public CustomerDto getCustomerById(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return CustomerDto.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }
}
