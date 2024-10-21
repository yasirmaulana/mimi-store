package io.github.yasirmaulana.mimi_store.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.class)
public class ResultPageResponseDTO<T> implements Serializable {
    private List<T> result;
    private Long page;
    private Long elements;
}
