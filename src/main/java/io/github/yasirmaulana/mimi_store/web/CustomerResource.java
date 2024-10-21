package io.github.yasirmaulana.mimi_store.web;

import io.github.yasirmaulana.mimi_store.dto.CustomerCreateRequestDTO;
import io.github.yasirmaulana.mimi_store.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@AllArgsConstructor
@RestController
public class CustomerResource {

    private final CustomerService customerService;

    @PostMapping(
            path = "/api/customer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> createNewCustomer(@RequestBody CustomerCreateRequestDTO dto) {
        customerService.createNewCustomer(dto);
        return ResponseEntity.created(URI.create("/")).build();
    }
}
