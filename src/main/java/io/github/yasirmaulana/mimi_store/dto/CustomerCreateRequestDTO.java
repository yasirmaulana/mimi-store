package io.github.yasirmaulana.mimi_store.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@JsonNaming(PropertyNamingStrategy.class)
public class CustomerCreateRequestDTO implements Serializable {
    private String name;
    private String email;
    private String hpNumber;
    private LocalDate birthDate;
    private String sex;
    private String address;
}
