package io.github.yasirmaulana.mimi_store.dto;

import lombok.Data;

@Data
public class MockApiResponse {
    private String status;
    private String code;
    private String message;
    private String data;
}
