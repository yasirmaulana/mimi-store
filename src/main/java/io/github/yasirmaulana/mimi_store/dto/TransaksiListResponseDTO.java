package io.github.yasirmaulana.mimi_store.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@JsonNaming(PropertyNamingStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
public class TransaksiListResponseDTO implements Serializable {
    private LocalDate tanggal;
    private Long pelangganId;
    private Long produkId;
    private Long jumlah;
    private Long hargaSatuan;
    private Long totalHarga;
    private String msisdn;
}
