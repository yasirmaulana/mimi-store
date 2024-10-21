package io.github.yasirmaulana.mimi_store.service;

import io.github.yasirmaulana.mimi_store.dto.CustomerCreateRequestDTO;

public interface CustomerService {
    void createNewCustomer(CustomerCreateRequestDTO dto);
}
