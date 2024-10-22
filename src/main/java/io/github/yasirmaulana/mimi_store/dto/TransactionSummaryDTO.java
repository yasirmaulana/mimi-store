package io.github.yasirmaulana.mimi_store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionSummaryDTO {
    private String yearmonth;
    private Long totalAmount;
    private Long totalTransaction;
}
