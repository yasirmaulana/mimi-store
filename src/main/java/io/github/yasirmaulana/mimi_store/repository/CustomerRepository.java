package io.github.yasirmaulana.mimi_store.repository;

import io.github.yasirmaulana.mimi_store.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
