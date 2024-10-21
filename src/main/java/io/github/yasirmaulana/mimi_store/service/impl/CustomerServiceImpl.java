package io.github.yasirmaulana.mimi_store.service.impl;

import io.github.yasirmaulana.mimi_store.domain.Customer;
import io.github.yasirmaulana.mimi_store.dto.CustomerCreateRequestDTO;
import io.github.yasirmaulana.mimi_store.repository.CustomerRepository;
import io.github.yasirmaulana.mimi_store.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void createNewCustomer(CustomerCreateRequestDTO dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setHpNumber(dto.getHpNumber());
        customer.setBirthDate(dto.getBirthDate());
        customer.setSex(dto.getSex());
        customer.setAddress(dto.getAddress());
        customerRepository.save(customer);
    }
}
